package com.ucompensar.facialrecognition.business.service;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.ucompensar.facialrecognition.business.dto.faceplus.DetectDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.ResultDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.SearchDto;
import com.ucompensar.facialrecognition.infrastructure.client.FacePlusClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacePlusServiceImpl implements FacePlusService {

   private final FacePlusClient facePlusClient;

   @Override
   public DetectDto detect(final String imageBase64) {
      return facePlusClient.detect(imageBase64);

   }

   @Override
   public ResultDto search(final String imageBase64, final String faceSetToken) {
      final SearchDto searchDto = facePlusClient.search(imageBase64, faceSetToken);
      return searchDto != null ? searchDto
            .getResults()
            .stream()
            .max(Comparator.comparing(ResultDto::getConfidence))
            .orElse(ResultDto.builder().build()) : null;
   }

   @Override
   public void addFace(final String tokenId, final String faceSetToken) {
      facePlusClient.addFace(tokenId, faceSetToken);
   }
}
