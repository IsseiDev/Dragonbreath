package com.flipdog.ads;

import android.app.Activity;
import android.location.Location;
import android.text.TextUtils;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlTargeting.TargetingGender;
import com.flipdog.commons.diagnostic.Track;
import com.google.ads.mediation.customevent.CustomEventBannerListener;
import com.millennialmedia.android.MMAd;
import com.millennialmedia.android.MMAdView;
import com.millennialmedia.android.MMException;
import com.millennialmedia.android.MMRequest;
import com.millennialmedia.android.RequestListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class MMUtils2
{
  public static RequestListener createListener(MMAdView paramMMAdView, final CustomEventBannerListener paramCustomEventBannerListener)
  {
    return new RequestListener()
    {
      public void MMAdOverlayLaunched(MMAd paramAnonymousMMAd)
      {
        paramCustomEventBannerListener.onPresentScreen();
      }

      public void MMAdRequestIsCaching(MMAd paramAnonymousMMAd)
      {
      }

      public void requestCompleted(MMAd paramAnonymousMMAd)
      {
        MMUtils2.this.setListener(null);
        paramCustomEventBannerListener.onReceivedAd(MMUtils2.this);
      }

      public void requestFailed(MMAd paramAnonymousMMAd, MMException paramAnonymousMMException)
      {
        MMUtils2.this.setListener(null);
        paramCustomEventBannerListener.onFailedToReceiveAd();
      }
    };
  }

  public static Hashtable<String, String> createMetaData()
  {
    Hashtable localHashtable = new Hashtable();
    AdWhirlTargeting.TargetingGender localTargetingGender = AdWhirlTargeting.getGender();
    String str2;
    label86: Iterator localIterator;
    if (localTargetingGender == AdWhirlTargeting.TargetingGender.MALE)
    {
      localHashtable.put("gender", "male");
      int i = AdWhirlTargeting.getAge();
      if (i != -1)
        localHashtable.put("age", String.valueOf(i));
      String str1 = AdWhirlTargeting.getPostalCode();
      if (!TextUtils.isEmpty(str1))
        localHashtable.put("zip", str1);
      if (AdWhirlTargeting.getKeywordSet() == null)
        break label154;
      str2 = TextUtils.join(",", AdWhirlTargeting.getKeywordSet());
      if (!TextUtils.isEmpty(str2))
        localHashtable.put("keywords", str2);
      localHashtable.put("vendor", "Admob mediator");
      localIterator = localHashtable.keySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return localHashtable;
        if (localTargetingGender != AdWhirlTargeting.TargetingGender.FEMALE)
          break;
        localHashtable.put("gender", "female");
        break;
        label154: str2 = null;
        break label86;
      }
      String str3 = (String)localIterator.next();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str3;
      arrayOfObject[1] = localHashtable.get(str3);
      track("map[%s] = %s", arrayOfObject);
    }
  }

  public static void handle(Activity paramActivity, String paramString, CustomEventBannerListener paramCustomEventBannerListener)
  {
    new MMHandler2().handle(paramActivity, paramString, paramCustomEventBannerListener);
  }

  public static void setLocation()
  {
    Location localLocation = AdWhirlTargeting.getLocation();
    if (localLocation != null)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Double.valueOf(localLocation.getLatitude());
      arrayOfObject[1] = Double.valueOf(localLocation.getLongitude());
      track("adView.updateUserLocation(location), latitude = %s, longitude = %s", arrayOfObject);
    }
    while (true)
    {
      MMRequest.setUserLocation(localLocation);
      return;
      track("adView.updateUserLocation(null)", new Object[0]);
    }
  }

  static void track(String paramString, Object[] paramArrayOfObject)
  {
    Track.me("Ads", paramString, paramArrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.MMUtils2
 * JD-Core Version:    0.6.2
 */