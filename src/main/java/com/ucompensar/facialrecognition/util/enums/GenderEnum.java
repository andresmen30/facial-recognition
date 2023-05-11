package com.ucompensar.facialrecognition.util.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum GenderEnum {

   MALE("Masculino"),
   FEMALE("Femenino"),
   WITHOUD_GENDER("Prefiero no decirlo");

   private final String name;

}
