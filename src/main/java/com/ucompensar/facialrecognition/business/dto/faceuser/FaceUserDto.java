package com.ucompensar.facialrecognition.business.dto.faceuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FaceUserDto {

   private String id;

   private String faceToken;

   private PersonalInformationDto personalInformation;

}
