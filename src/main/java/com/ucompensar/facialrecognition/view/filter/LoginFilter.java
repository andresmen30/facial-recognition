package com.ucompensar.facialrecognition.view.filter;

import java.io.IOException;

import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;

import jakarta.faces.application.ResourceHandler;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName = "Security", urlPatterns = { "/facial-recognition/pages/*", "/pages/*" }, dispatcherTypes = { DispatcherType.FORWARD,
      DispatcherType.REQUEST, DispatcherType.ERROR })
public class LoginFilter extends HttpFilter {

   @Override
   public void doFilter(final HttpServletRequest request, final HttpServletResponse response, final HttpSession session, final FilterChain chain)
         throws ServletException, IOException {

      if (!request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) {
         response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
         response.setHeader("Pragma", "no-cache");
         response.setDateHeader("Expires", 0);
      }
      if (session != null && session.getAttribute("user") != null) {
         chain.doFilter(request, response);
      } else {
         Servlets.facesRedirect(request, response, "/login.xhtml");
      }
   }

}
