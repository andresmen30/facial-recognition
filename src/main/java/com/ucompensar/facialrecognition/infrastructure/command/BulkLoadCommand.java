package com.ucompensar.facialrecognition.infrastructure.command;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ucompensar.facialrecognition.business.service.AttributeFaceService;
import com.ucompensar.facialrecognition.business.service.FacePlusService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BulkLoadCommand implements CommandLineRunner {

   @Value("${face.plus.facesets.token}")
   private String faceSetsToken;

   @Value("${face.plus.facesets.intruder.token}")
   private String faceSetsTokenIntruder;

   private final AttributeFaceService attributeFaceService;

   private final FacePlusService facePlusService;

   @Override
   public void run(final String... args) throws ParseException {
      //saveImage();
      /*final ArrayList<Attribute> attributes = Arrays.stream(AttributesDataEnum.values()).map(attributesDataEnum -> {
         if (attributesDataEnum.getAttributeValues() != null) {
            return new Attribute(attributesDataEnum.getHeaderName(), attributesDataEnum.getAttributeValues());
         } else if (StringUtils.isNotEmpty(attributesDataEnum.getFormat())) {
            return new Attribute(attributesDataEnum.getHeaderName(), attributesDataEnum.getFormat());
         } else {
            return new Attribute(attributesDataEnum.getHeaderName(), true);
         }
      }).collect(Collectors.toCollection(ArrayList::new));
      Instances data = new Instances("Facial recognition", attributes, 0);
      final List<AttributesFaceDto> list = attributeFaceService.getAll();
      for (final AttributesFaceDto userFace : list) {
         final Instance instance = new DenseInstance(attributes.size());
         instance.setValue(attributes.get(0), attributes.get(0).indexOfValue(userFace.getIntruder().getDescription()));
         instance.setValue(attributes.get(1), attributes.get(1).indexOfValue(userFace.getGlass()));
         instance.setValue(attributes.get(2), attributes.get(2).indexOfValue(userFace.getDetectedEmotion()));
         instance.setValue(attributes.get(3), attributes.get(3).parseDate(LocalDateUtil.localDateToString(userFace.getDateAccess())));
         data.add(instance);
      }
      final J48 classifier = new J48();
      try {
         data.setClassIndex(NumberUtils.INTEGER_ZERO);
         final NumericToNominal filter = new NumericToNominal();
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
      }*/
   }

  /* private void saveImage() {
      final File folder = new File("train/fearful");
      if (folder != null && folder.isDirectory() && folder.exists()) {
         for (final File image : folder.listFiles()) {
            try {
               byte[] fileContent = Files.readAllBytes(image.toPath());
               final String imageBase64 = Base64.getEncoder().encodeToString(fileContent);
               saveFaces(imageBase64);
               image.delete();
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
         }

      } else {
         log.error("No se ha encontrado el folder");
      }

   }*/

  /* private boolean saveFaces(final String imageBase64) {
      boolean isSave = false;
      try {
         TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
      final DetectDto detectDto = facePlusService.detect(imageBase64);
      if (detectDto.getFaces().size() > 0) {
         final FaceDto faceDto = detectDto.getFaces().get(NumberUtils.INTEGER_ZERO);
         final AttributesFaceDto attributesFaceDto = AttributesFaceDto
               .builder()
               .faceUser(null)
               .dateAccess(LocalDateUtil.randomLocalDateTime())
               .fear(faceDto.getAttributes().getEmotion().getFear())
               .anger(faceDto.getAttributes().getEmotion().getAnger())
               .happiness(faceDto.getAttributes().getEmotion().getHappiness())
               .disgust(faceDto.getAttributes().getEmotion().getDisgust())
               .neutral(faceDto.getAttributes().getEmotion().getNeutral())
               .sadness(faceDto.getAttributes().getEmotion().getSadness())
               .surprise(faceDto.getAttributes().getEmotion().getSurprise())
               .intruder(DecisionEnum.YES)
               .glass(faceDto.getAttributes().getGlass().getValue())
               .image(imageBase64)
               .smile(faceDto.getAttributes().getSmile().getValue())
               .build();
         final String emotionDetected = FaceUtil.emotion(attributesFaceDto);
         log.info("Emotion detected -> {}", emotionDetected);
         if (emotionDetected.equals(EmotionEnum.FEAR.getDescription())) {
            log.info("starting save...");
            attributeFaceService.save(attributesFaceDto);
            facePlusService.addFace(faceDto.getFaceToken(), this.faceSetsTokenIntruder);
            isSave = true;
         }
      } else {
         log.info("No se ha encontrado ningun rostro");

      }
      return isSave;
   }*/

}
