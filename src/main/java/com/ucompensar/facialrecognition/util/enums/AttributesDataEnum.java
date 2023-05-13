package com.ucompensar.facialrecognition.util.enums;

import java.util.Arrays;
import java.util.List;

import com.ucompensar.facialrecognition.util.date.LocalDateUtil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import weka.core.Attribute;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum AttributesDataEnum {

   INTRUDER("intruder", Attribute.STRING, null, Arrays.stream(DecisionEnum.values()).map(DecisionEnum::getDescription).toList()),
   GLASS("glass", Attribute.STRING, null, Arrays.stream(GlassEnum.values()).map(GlassEnum::getDescription).toList()),
   EMOTION("emotion", Attribute.STRING, null, Arrays.stream(EmotionEnum.values()).map(EmotionEnum::getDescription).toList()),
   DATE_ACCESS("dateAccess", null, LocalDateUtil.DATE_FORMAT, null);

   private final String headerName;

   private final Integer attributeIndex;

   private final String format;

   private final List<String> attributeValues;

}
