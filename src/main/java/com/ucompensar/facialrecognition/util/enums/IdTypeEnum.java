package com.ucompensar.facialrecognition.util.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum IdTypeEnum {

   CC("Cedula de ciudadania"),
   CE("Cedula de extranjeria"),
   TI("Tarjeta de identidad"),
   PAP("Pasaporte");

   private final String description;

}
