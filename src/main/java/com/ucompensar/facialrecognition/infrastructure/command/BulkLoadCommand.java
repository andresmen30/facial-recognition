package com.ucompensar.facialrecognition.infrastructure.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ucompensar.facialrecognition.infrastructure.entity.AttributesFace;
import com.ucompensar.facialrecognition.infrastructure.repository.AttributeFaceRepository;
import com.ucompensar.facialrecognition.util.enums.AttributesDataEnum;

import lombok.RequiredArgsConstructor;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Debug;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

@Component
@RequiredArgsConstructor
public class BulkLoadCommand implements CommandLineRunner {

   private final AttributeFaceRepository faceUserAuditRepository;

   @Override
   public void run(final String... args) {
      final ArrayList<Attribute> attributes = Arrays.stream(AttributesDataEnum.values()).map(attributesDataEnum -> {
         if (attributesDataEnum.getAttributeValues() != null) {
            return new Attribute(attributesDataEnum.getHeaderName(), attributesDataEnum.getAttributeValues());
         } else if (attributesDataEnum.getAttributeIndex() == Attribute.NUMERIC) {
            return new Attribute(attributesDataEnum.getHeaderName(), Attribute.NUMERIC);
         } else {
            return new Attribute(attributesDataEnum.getHeaderName(), true);
         }
      }).collect(Collectors.toCollection(ArrayList::new));
      Instances data = new Instances("Facial recognition", attributes, 0);
      final List<AttributesFace> list = faceUserAuditRepository.findAll();
      for (final AttributesFace userFace : list) {
         final Instance instance = new DenseInstance(attributes.size());
         instance.setValue(attributes.get(0), attributes.get(0).indexOfValue(userFace.getIntruder().getDescription()));
         instance.setValue(attributes.get(1), attributes.get(1).indexOfValue(userFace.getGlass()));
         instance.setValue(attributes.get(2), userFace.getAnger());
         instance.setValue(attributes.get(3), userFace.getDisgust());
         instance.setValue(attributes.get(4), userFace.getFear());
         instance.setValue(attributes.get(5), userFace.getNeutral());
         instance.setValue(attributes.get(6), userFace.getSadness());
         instance.setValue(attributes.get(7), userFace.getSurprise());
         instance.setValue(attributes.get(8), userFace.getHappiness());
         data.add(instance);
      }
      final J48 classifier = new J48();
      try {
         data.setClassIndex(NumberUtils.INTEGER_ZERO);
         NumericToNominal filter = new NumericToNominal();
         filter.setInputFormat(data);
         data = Filter.useFilter(data, filter);
         classifier.buildClassifier(data);
         final Evaluation evaluation = new Evaluation(data);
         evaluation.crossValidateModel(classifier, data, data.numInstances(), new Debug.Random(1));
         System.out.println(evaluation.toSummaryString());
         System.out.println(classifier.graph());
         System.out.println(classifier.prefix());
         System.out.println(evaluation.toMatrixString());
         System.out.println(evaluation.toClassDetailsString());
         System.out.println("Accuracy: " + evaluation.pctCorrect() + "%");
         System.out.println("Precision: " + evaluation.weightedPrecision());
         System.out.println("Recall: " + evaluation.weightedRecall());
         System.out.println("F1 Score: " + evaluation.weightedFMeasure());
      } catch (Exception exception) {
         System.err.println("error " + exception.getMessage());
      }
   }

}
