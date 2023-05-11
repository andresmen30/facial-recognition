package com.ucompensar.facialrecognition;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacialRecognitionApplication implements CommandLineRunner {

   @Override
   public void run(final String... args) {

   }

   public static void main(String[] args) {
      SpringApplication.run(FacialRecognitionApplication.class, args);
   }

}
