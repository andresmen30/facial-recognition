package com.ucompensar.facialrecognition.util.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum GlassEnum {

   NONE("None"),
   DARK("Dark"),
   NORMAL("Normal");

   private final String description;

}
