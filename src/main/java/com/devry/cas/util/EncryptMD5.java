package com.devry.cas.util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

public class EncryptMD5
{
  public static final String ENCODING = "ISO-8859-1";
  public static final String MESSAGE_DIGEST_ALGORITHM = "MD5";
  private Logger logger = Logger.getLogger(EncryptMD5.class);
  
  public String createMD5String(String dvValue, String secretKey)
  {
    return encryptByMD5(dvValue + "&" + String.valueOf(Calendar.getInstance().getTime().getTime()) + "&" + secretKey);
  }
  
  public String createMD5String(String dvValue, String time, String secretKey)
  {
    return encryptByMD5(dvValue + time + secretKey);
  }
  
  public String encryptByMD5(String cleartext)
  {
    String md5String = null;
    if (cleartext != null) {
      try
      {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] data = cleartext.getBytes("ISO-8859-1");
        md.update(data);
        byte[] digestedByteArray = md.digest();
        
        StringBuffer sb = new StringBuffer(digestedByteArray.length * 2);
        for (int i = 0; i < digestedByteArray.length; i++)
        {
          sb.append(convertDigit(digestedByteArray[i] >> 4));
          sb.append(convertDigit(digestedByteArray[i] & 0xF));
        }
        md5String = sb.toString();
        if (md5String != null) {
          md5String = md5String.toLowerCase();
        }
      }
      catch (NoSuchAlgorithmException ns)
      {
        this.logger.warn("Error occured while encoding the cleartext string. " + ns);
        md5String = null;
      }
      catch (UnsupportedEncodingException ex)
      {
        this.logger.warn("Error occured while encoding the cleartext string. " + ex);
        md5String = null;
      }
    }
    return md5String;
  }
  
  public static String convert(byte[] bytes)
  {
    StringBuffer sb = new StringBuffer(bytes.length * 2);
    for (int i = 0; i < bytes.length; i++)
    {
      sb.append(convertDigit(bytes[i] >> 4));
      sb.append(convertDigit(bytes[i] & 0xF));
    }
    return sb.toString();
  }
  
  private static char convertDigit(int value)
  {
    value &= 0xF;
    char digitToCharValue = value >= 10 ? (char)(value - 10 + 97) : (char)(value + 48);
    return digitToCharValue;
  }
  
  public static void main(String[] args)
  {
    System.out.println(convertDigit(12));
    System.out.println(convertDigit(4));
    System.out.println(convertDigit(15));
  }
}
