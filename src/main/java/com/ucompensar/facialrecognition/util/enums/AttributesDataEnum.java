package com.ucompensar.facialrecognition.util.enums;

import java.util.Arrays;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import weka.core.Attribute;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum AttributesDataEnum {

   INTRUDER("intruder", Attribute.STRING, Arrays.stream(DecisionEnum.values()).map(DecisionEnum::getDescription).toList()),
   GLASS("glass", Attribute.STRING, Arrays.stream(GlassEnum.values()).map(GlassEnum::getDescription).toList()),
   ANGER("anger", Attribute.NUMERIC, null),
   DISGUST("disgust", Attribute.NUMERIC, null),
   FEAR("fear", Attribute.NUMERIC, null),
   NEUTRAL("neutral", Attribute.NUMERIC, null),
   SADNESS("sadness", Attribute.NUMERIC, null),
   SURPRISE("surprise", Attribute.NUMERIC, null),
   HAPPINESS("happiness", Attribute.NUMERIC, null);
   //DATE_ACCESS("dateAccess", Attribute.STRING),


   private final String headerName;

   private final Integer attributeIndex;

   private final List<String> attributeValues;

}
