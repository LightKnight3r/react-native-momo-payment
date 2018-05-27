package momo.momo_partner.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.ComponentName;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.os.Environment;
import android.util.Log;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.os.Bundle;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Arrays;
import java.lang.SecurityException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

import momo.momo_partner.demo.PaymentActivity;


public class MomoPaymentModule extends ReactContextBaseJavaModule {

    private static final String TAG = MomoPaymentModule.class.getSimpleName();

    private ReactApplicationContext reactContext;

    public MomoPaymentModule(ReactApplicationContext reactContext) {
      super(reactContext);
      this.reactContext = reactContext;
    }

    @Override
    public String getName() {
      return "MomoPaymentModule";
    }

    @ReactMethod
    public void requestPayment(String merchantname, String merchantnamelabel, String merchantcode, String amount, String description, String language, String username, String fee, int environment) {

      Bundle data = new Bundle();

      data.putString("merchantname", merchantname);
      data.putString("merchantnamelabel", merchantnamelabel);
      data.putString("merchantcode", merchantcode);
      data.putString("amount", amount);
      data.putString("description", description);
      data.putString("language", language);
      data.putString("username", username);
      data.putString("fee", fee);
      data.putInt("environment", environment);

      Intent intent = new Intent(this.reactContext, PaymentActivity.class);
      intent.putExtras(data);
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP );
      this.reactContext.startActivity(intent);
    }

}
