package com.ucompensar.facialrecognition.business.service;

import com.ucompensar.facialrecognition.business.dto.faceplus.DetectDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.ResultDto;

public interface FacePlusService {

   DetectDto detect(final String imageBase64);

   ResultDto search(final String imageBase64, final String faceSetToken);

   void addFace(final String tokenId, final String faceSetToken);

}
