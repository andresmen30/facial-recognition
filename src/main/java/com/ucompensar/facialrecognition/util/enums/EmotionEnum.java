package com.ucompensar.facialrecognition.util.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum EmotionEnum {

   ANGER("anger"),
   FEAR("fear"),
   NEUTRAL("neutral"),
   SADNESS("sadness"),
   HAPPINESS("happiness"),
   DISGUST("disgust"),
   SURPRISE("surprise");

   private final String description;
}
