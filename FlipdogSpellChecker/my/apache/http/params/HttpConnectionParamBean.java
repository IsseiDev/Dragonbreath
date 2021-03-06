package my.apache.http.params;

public class HttpConnectionParamBean extends HttpAbstractParamBean
{
  public HttpConnectionParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setConnectionTimeout(int paramInt)
  {
    HttpConnectionParams.setConnectionTimeout(this.params, paramInt);
  }

  public void setLinger(int paramInt)
  {
    HttpConnectionParams.setLinger(this.params, paramInt);
  }

  public void setSoTimeout(int paramInt)
  {
    HttpConnectionParams.setSoTimeout(this.params, paramInt);
  }

  public void setSocketBufferSize(int paramInt)
  {
    HttpConnectionParams.setSocketBufferSize(this.params, paramInt);
  }

  public void setStaleCheckingEnabled(boolean paramBoolean)
  {
    HttpConnectionParams.setStaleCheckingEnabled(this.params, paramBoolean);
  }

  public void setTcpNoDelay(boolean paramBoolean)
  {
    HttpConnectionParams.setTcpNoDelay(this.params, paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.HttpConnectionParamBean
 * JD-Core Version:    0.6.2
 */