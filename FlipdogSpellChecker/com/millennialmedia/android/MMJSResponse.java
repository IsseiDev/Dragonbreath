package com.millennialmedia.android;

import org.json.JSONException;
import org.json.JSONObject;

class MMJSResponse
{
  static final int ERROR = 0;
  static final int SUCCESS = 1;
  String className;
  byte[] dataResponse;
  String methodName;
  Object response;
  int result;

  static MMJSResponse responseWithError()
  {
    return responseWithError("An unknown error occured.");
  }

  static MMJSResponse responseWithError(String paramString)
  {
    MMJSResponse localMMJSResponse = new MMJSResponse();
    localMMJSResponse.result = 0;
    localMMJSResponse.response = paramString;
    return localMMJSResponse;
  }

  static MMJSResponse responseWithSuccess()
  {
    return responseWithSuccess("Success.");
  }

  static MMJSResponse responseWithSuccess(String paramString)
  {
    MMJSResponse localMMJSResponse = new MMJSResponse();
    localMMJSResponse.result = 1;
    localMMJSResponse.response = paramString;
    return localMMJSResponse;
  }

  String toJSONString()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      if (this.className != null)
        localJSONObject.put("class", this.className);
      if (this.methodName != null)
        localJSONObject.put("call", this.methodName);
      localJSONObject.put("result", this.result);
      if (this.response != null)
        localJSONObject.put("response", this.response);
      while (true)
      {
        return localJSONObject.toString();
        if (this.dataResponse == null)
          break;
        localJSONObject.put("response", Base64.encodeToString(this.dataResponse, false));
      }
    }
    catch (JSONException localJSONException)
    {
      MMSDK.Log.e(localJSONException.getMessage());
      return "";
    }
    return "";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMJSResponse
 * JD-Core Version:    0.6.2
 */