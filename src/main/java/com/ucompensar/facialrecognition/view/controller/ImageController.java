package com.ucompensar.facialrecognition.view.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.context.annotation.ApplicationScope;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScope
@Named
public class ImageController implements Serializable {

   @Getter
   @Setter
   private InputStream imageIntruder;

   @PostConstruct
   public void init() {
      clean();
   }

   public void clean() {
      this.imageIntruder = null;
   }

   @SneakyThrows
   public void openDialog(final String imageBase64) {
      TimeUnit.SECONDS.sleep(1);
      loadImage(imageBase64);
   }

   public void loadImage(final String imageBase64) {
      if (StringUtils.isNotEmpty(imageBase64)) {
         final byte[] decodedBytes = Base64.decodeBase64(imageBase64);
         this.imageIntruder = new ByteArrayInputStream(decodedBytes);
      }
   }

}
