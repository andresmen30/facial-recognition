package com.ucompensar.facialrecognition.business.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ucompensar.facialrecognition.util.date.LocalDateUtil;

import lombok.Cleanup;
import lombok.SneakyThrows;

@Service
public class MailServiceImpl implements MailService {

   @Value("${mail.smtp.host}")
   private String host;

   @Value("${mail.smtp.port}")
   private int port;

   @Value("${mail.smtp.ssl}")
   private boolean ssl;

   @Value("${mail.smtp.username}")
   private String userName;

   @Value("${mail.smtp.password}")
   private String password;

   @Override
   @Async
   public void sendMailHtml(final String img) {
      final HtmlEmail email = new HtmlEmail();
      email.setAuthentication(this.userName, this.password);
      email.setSmtpPort(this.port);
      email.setSSLOnConnect(this.ssl);
      email.setHostName(this.host);
      email.setCharset(StandardCharsets.UTF_8.name());
      try {
         email.addTo("andresmen30@gmail.com", "Facial recognition");
         email.setFrom(this.userName, "Facial recognition");
         email.setSubject("Mensaje de alerta");
         email.setHtmlMsg(htmlContent(email, img));
         email.send();
      } catch (EmailException e) {
         throw new RuntimeException(e);
      }

   }

   @SneakyThrows(IOException.class)
   private String htmlContent(final HtmlEmail email, final String img) {
      final File file = new File("email/emailAlert.html");
      final Document document = Jsoup.parse(file);
      final String html = document.html();
      return builder(email, html, img);

   }

   @SneakyThrows({ IOException.class, EmailException.class })
   private String builder(final HtmlEmail email, final String htmlString, final String img) {
      byte[] decodedBytes = Base64.getDecoder().decode(img);
      @Cleanup("deleteOnExit")
      final File file = new File(System.getProperty("java.io.tmpdir").concat("/face.jpeg"));
      FileUtils.writeByteArrayToFile(file, decodedBytes);
      return htmlString + "<div class=\"container\">" + "<div class=\"alert alert-danger alerta\" role=\"alert\">"
            + "<h4 class=\"alert-heading\">¡Alerta de intruso!</h4>" + "<img src=cid:" + email.embed(file) + "alt=\"imagen de intruso\">" + "<hr>"
            + "<p class=\"mb-0\">Fecha de detección: <span id=\"hora\">" + LocalDateUtil.localDateToString(LocalDateTime.now()) + "</span></p>"
            + "</div></div></body></html>";
   }

}
