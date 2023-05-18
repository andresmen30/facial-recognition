package com.ucompensar.facialrecognition.util;

import java.util.HashMap;
import java.util.Map;

import com.ucompensar.facialrecognition.business.dto.faceplus.AttributesDto;
import com.ucompensar.facialrecognition.business.dto.faceuser.AttributesFaceDto;
import com.ucompensar.facialrecognition.infrastructure.entity.AttributesFace;
import com.ucompensar.facialrecognition.util.enums.EmotionEnum;

public class FaceUtil {

   public static String emotion(final AttributesFace attributesFace) {
      final HashMap<String, Double> emotions = new HashMap<>();
      emotions.put(EmotionEnum.ANGER.getDescription(), attributesFace.getAnger());
      emotions.put(EmotionEnum.FEAR.getDescription(), attributesFace.getFear());
      emotions.put(EmotionEnum.NEUTRAL.getDescription(), attributesFace.getNeutral());
      emotions.put(EmotionEnum.SADNESS.getDescription(), attributesFace.getSadness());
      emotions.put(EmotionEnum.HAPPINESS.getDescription(), attributesFace.getHappiness());
      emotions.put(EmotionEnum.DISGUST.getDescription(), attributesFace.getDisgust());
      emotions.put(EmotionEnum.SURPRISE.getDescription(), attributesFace.getSurprise());
      return emotions.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

   }

   public static String emotion(final AttributesFaceDto attributesFace) {
      final HashMap<String, Double> emotions = new HashMap<>();
      emotions.put(EmotionEnum.ANGER.getDescription(), attributesFace.getAnger());
      emotions.put(EmotionEnum.FEAR.getDescription(), attributesFace.getFear());
      emotions.put(EmotionEnum.NEUTRAL.getDescription(), attributesFace.getNeutral());
      emotions.put(EmotionEnum.SADNESS.getDescription(), attributesFace.getSadness());
      emotions.put(EmotionEnum.HAPPINESS.getDescription(), attributesFace.getHappiness());
      emotions.put(EmotionEnum.DISGUST.getDescription(), attributesFace.getDisgust());
      emotions.put(EmotionEnum.SURPRISE.getDescription(), attributesFace.getSurprise());
      return emotions.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

   }

   public static boolean detectedIntruder(final AttributesDto attributesFace, final Integer probability) {
      return attributesFace.getEmotion().getFear() >= probability || attributesFace.getEmotion().getSurprise() >= probability;

   }

}
