package com.ucompensar.facialrecognition.business.service;

import com.ucompensar.facialrecognition.business.dto.faceplus.AttributesDto;
import com.ucompensar.facialrecognition.business.dto.faceplus.FaceDto;
import com.ucompensar.facialrecognition.business.dto.faceuser.FaceUserDto;

public interface FaceUserService {

   void save(final FaceUserDto faceUserDto, final FaceDto faceDto, final String imageBase64, final String faceSetsToken);

   void saveLog(final String faceToken, final AttributesDto attributesDto, final String imageBase64);

   void saveLogIntruder(final String faceToken, final AttributesDto attributesDto, final String imageBase64, final String faceSetsToken);

}
