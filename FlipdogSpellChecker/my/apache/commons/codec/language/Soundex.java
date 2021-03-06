package my.apache.commons.codec.language;

import my.apache.commons.codec.EncoderException;
import my.apache.commons.codec.StringEncoder;

public class Soundex
  implements StringEncoder
{
  public static final Soundex US_ENGLISH = new Soundex();
  private static final char[] US_ENGLISH_MAPPING = "01230120022455012623010202".toCharArray();
  public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
  private int maxLength = 4;
  private final char[] soundexMapping;

  public Soundex()
  {
    this.soundexMapping = US_ENGLISH_MAPPING;
  }

  public Soundex(String paramString)
  {
    this.soundexMapping = paramString.toCharArray();
  }

  public Soundex(char[] paramArrayOfChar)
  {
    this.soundexMapping = new char[paramArrayOfChar.length];
    System.arraycopy(paramArrayOfChar, 0, this.soundexMapping, 0, paramArrayOfChar.length);
  }

  private char getMappingCode(String paramString, int paramInt)
  {
    char c1 = map(paramString.charAt(paramInt));
    if ((paramInt > 1) && (c1 != '0'))
    {
      int i = paramString.charAt(paramInt - 1);
      if ((72 == i) || (87 == i))
      {
        char c2 = paramString.charAt(paramInt - 2);
        if ((map(c2) == c1) || ('H' == c2) || ('W' == c2))
          c1 = '\000';
      }
    }
    return c1;
  }

  private char[] getSoundexMapping()
  {
    return this.soundexMapping;
  }

  private char map(char paramChar)
  {
    int i = paramChar - 'A';
    if ((i < 0) || (i >= getSoundexMapping().length))
      throw new IllegalArgumentException("The character is not mapped: " + paramChar);
    return getSoundexMapping()[i];
  }

  public int difference(String paramString1, String paramString2)
    throws EncoderException
  {
    return SoundexUtils.difference(this, paramString1, paramString2);
  }

  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (!(paramObject instanceof String))
      throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
    return soundex((String)paramObject);
  }

  public String encode(String paramString)
  {
    return soundex(paramString);
  }

  public int getMaxLength()
  {
    return this.maxLength;
  }

  public void setMaxLength(int paramInt)
  {
    this.maxLength = paramInt;
  }

  public String soundex(String paramString)
  {
    if (paramString == null)
      return null;
    String str = SoundexUtils.clean(paramString);
    if (str.length() == 0)
      return str;
    char[] arrayOfChar = { 48, 48, 48, 48 };
    int i = 1;
    int j = 1;
    arrayOfChar[0] = str.charAt(0);
    int k = getMappingCode(str, 0);
    while (true)
    {
      if ((i >= str.length()) || (j >= arrayOfChar.length))
        return new String(arrayOfChar);
      int m = i + 1;
      int n = getMappingCode(str, i);
      if (n != 0)
      {
        if ((n != 48) && (n != k))
        {
          int i1 = j + 1;
          arrayOfChar[j] = n;
          j = i1;
        }
        k = n;
        i = m;
      }
      else
      {
        i = m;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.language.Soundex
 * JD-Core Version:    0.6.2
 */