package com.ucompensar.facialrecognition.view.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ReportController {

   @Getter
   @Setter
   private String prueba;

   @PostConstruct
   public void init() {
      prueba = "HOLA MANO";
   }

}
