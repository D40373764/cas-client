package com.devry.cas;

import com.devry.cas.util.EncryptMD5;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CasClientServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  static final Logger logger = LoggerFactory.getLogger(CasClientServlet.class);
  private static final String url = "https://portal.thedevrycommons.com/portal.jsp?y3uQUnbK9L2lCdFz81r784VV37Pyjnlec8BAIFewo9I=";
  private static final String secretKey = "JzY2jAMP3nmzutd";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String timestamp = String.valueOf(System.currentTimeMillis());
    String username = request.getRemoteUser();
    
    EncryptMD5 md5 = new EncryptMD5();
    String token = md5.createMD5String(username, timestamp, "JzY2jAMP3nmzutd");
    
    RequestDispatcher loginPostDispatcher = request.getRequestDispatcher("jsp/smartlinkdynamiclogin.jsp");
    
    Map<String, String> formLoginParameters = new HashMap();
    formLoginParameters.put("method", "post");
    formLoginParameters.put("url", "https://portal.thedevrycommons.com/portal.jsp?y3uQUnbK9L2lCdFz81r784VV37Pyjnlec8BAIFewo9I=");
    formLoginParameters.put("timestamp", timestamp);
    formLoginParameters.put("token", token.toLowerCase());
    formLoginParameters.put("username", username);
    
    request.setAttribute("formLoginParameters", formLoginParameters);
    loginPostDispatcher.forward(request, response);
  }
}
