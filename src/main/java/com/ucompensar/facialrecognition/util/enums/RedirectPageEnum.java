package com.ucompensar.facialrecognition.util.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public enum RedirectPageEnum {

   LOGIN("login", "/login", "/login.xhtml"),
   REPORT("report", "/report", "pages/report.xhtml"),

   REGISTER_USER("register", "/register", "/registerUser.xhtml");

   private final String id;

   private final String pattern;

   private final String view;
}
