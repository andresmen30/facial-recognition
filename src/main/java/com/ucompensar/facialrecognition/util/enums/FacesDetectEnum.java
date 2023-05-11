package com.ucompensar.facialrecognition.util.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum FacesDetectEnum {

   FACE_DETECT_ONE(1, "Se ha detectado correctamente el rostro"),
   FACE_DETECT_TWO(2, "Se han detectado mas de 1 rostro"),
   FACE_DETECT_THREE(3, "No se ha procesado la detección del rostro, por favor procure no moverse"),
   FACE_DETECT_FOUR(4, "Al tener gafas, por favor no cerrar los ojos"),
   FACE_DETECT_FIVE(5, "Por favor no cerrar los ojos"),
   FACE_DETECT_ERROR(10, "Error al leer el rostro");

   private final int faceNum;

   private final String message;

}
