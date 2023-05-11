package com.ucompensar.facialrecognition.business.dto.faceuser;

import java.time.LocalDateTime;

import com.ucompensar.facialrecognition.util.enums.DecisionEnum;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class FaceUserAuditDto {

   private LocalDateTime dateAccess;

   private String image;

   private DecisionEnum intruder;

   private FaceUserDto faceUser;

}
