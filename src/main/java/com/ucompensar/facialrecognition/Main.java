package com.ucompensar.facialrecognition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import com.ucompensar.facialrecognition.business.service.MailService;
import com.ucompensar.facialrecognition.business.service.MailServiceImpl;

public class Main {

   public static void main(String... args) throws IOException {
      File file = new File("/Users/andresmendez/Desktop/test.jpg");
      final String img = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
      MailService mailService = new MailServiceImpl();
      mailService.sendMailHtml(img);
   }

}
