package com.ucompensar.facialrecognition.util.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum VariablesHtmlEnum {

   IMAGE_ACCESS_ALERT("{IMAGE_ACCESS_ALERT}"),
   DATE_ACCESS("{DATE_ACCESS}");

   private final String description;

}
