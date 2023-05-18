package com.ucompensar.facialrecognition.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.ucompensar.facialrecognition.business.dto.faceplus.DetectDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.SearchDto;

@Service
public class FacePlusClient {

   private static final String ENDPOINT_DETECT = "/detect";

   private static final String ENDPOINT_SEARCH = "/search";

   private static final String ENDPOINT_ADD_FACE = "/faceset/async/addface";
   private static final String ENDPOINT_REMOVE_FACE = "/faceset/removeface";

   private static final String PARAM_API_KEY = "api_key";

   private static final String PARAM_API_SECRET = "api_secret";

   private static final String PARAM_IMAGE_BASE_64 = "image_base64";

   private static final String PARAM_FACE_SET_TOKEN = "faceset_token";

   private static final String PARAM_FACE_TOKENS = "face_tokens";

   private static final String PARAM_RETURN_ATTRIBUTES = "return_attributes";

   @Value("${face.plus.base.url}")
   private String baseUrl;

   @Value("${face.plus.api.key}")
   private String apiKey;

   @Value("${face.plus.api.secret}")
   private String apiSecret;

   @Value("${face.plus.detect.return.attributes}")
   private String returnAttributes;

   public WebClient getWebClient() {
      return WebClient.builder().baseUrl(this.baseUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE).build();
   }

   public DetectDto detect(final String imageBase64) {
      return this
            .getWebClient()
            .post()
            .uri(ENDPOINT_DETECT)
            .body(BodyInserters
                  .fromFormData(PARAM_API_KEY, this.apiKey)
                  .with(PARAM_API_SECRET, this.apiSecret)
                  .with(PARAM_RETURN_ATTRIBUTES, this.returnAttributes)
                  .with(PARAM_IMAGE_BASE_64, imageBase64))
            .retrieve()
            .bodyToMono(DetectDto.class)
            .block();
   }

   public SearchDto search(final String imageBase64, final String faceSetToken) {
      try {
         return this
               .getWebClient()
               .post()
               .uri(ENDPOINT_SEARCH)
               .body(BodyInserters
                     .fromFormData(PARAM_API_KEY, this.apiKey)
                     .with(PARAM_API_SECRET, this.apiSecret)
                     .with(PARAM_FACE_SET_TOKEN, faceSetToken)
                     .with(PARAM_IMAGE_BASE_64, imageBase64))
               .retrieve()
               .bodyToMono(SearchDto.class)
               .block();
      } catch (WebClientResponseException responseException) {
         return null;
      }
   }

   public void addFace(final String faceToken, final String faceSetToken) {
      this
            .getWebClient()
            .post()
            .uri(ENDPOINT_ADD_FACE)
            .body(BodyInserters
                  .fromFormData(PARAM_API_KEY, this.apiKey)
                  .with(PARAM_API_SECRET, this.apiSecret)
                  .with(PARAM_FACE_TOKENS, faceToken)
                  .with(PARAM_FACE_SET_TOKEN, faceSetToken))
            .retrieve()
            .bodyToMono(String.class)
            .block();
   }

   public void removeFace(final String faceToken, final String faceSetToken) {
      this
            .getWebClient()
            .post()
            .uri(ENDPOINT_REMOVE_FACE)
            .body(BodyInserters
                  .fromFormData(PARAM_API_KEY, this.apiKey)
                  .with(PARAM_API_SECRET, this.apiSecret)
                  .with(PARAM_FACE_TOKENS, faceToken)
                  .with(PARAM_FACE_SET_TOKEN, faceSetToken))
            .retrieve()
            .bodyToMono(String.class)
            .block();
   }

}
