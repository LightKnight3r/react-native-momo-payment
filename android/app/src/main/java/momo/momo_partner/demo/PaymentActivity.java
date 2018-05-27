package momo.momo_partner.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import android.util.Log;

import vn.momo.momo_partner.AppMoMoLib;
import vn.momo.momo_partner.MoMoParameterNamePayment;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.ReactActivity;

public class PaymentActivity extends ReactActivity {
    private static final String TAG = "PaymentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle data = getIntent().getExtras();
        String merchantname = data.getString("merchantname");
        Log.d(TAG,"ahihi"+merchantname);
        String merchantnamelabel = data.getString("merchantnamelabel");
        Log.d(TAG,"ahihi"+merchantnamelabel);
        String merchantcode = data.getString("merchantcode");
        Log.d(TAG,"ahihi"+merchantcode);
        String amount = data.getString("amount");
        Log.d(TAG,"ahihi"+amount);
        String description = data.getString("description");
        Log.d(TAG,"ahihi"+description);
        String language = data.getString("language");
        Log.d(TAG,"ahihi"+language);
        String username = data.getString("username");
        Log.d(TAG,"ahihi"+username);
        String fee = data.getString("fee");
        Log.d(TAG,"ahihi"+fee);
        int environment = data.getInt("environment");
        Log.d(TAG,"ahihi"+environment);

        if(environment == 0){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEBUG);
        }else if(environment == 1){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
        }else if(environment == 2){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.PRODUCTION);
        }
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);


        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put(MoMoParameterNamePayment.MERCHANT_NAME, merchantname);
        eventValue.put(MoMoParameterNamePayment.MERCHANT_NAME_LABEL, merchantnamelabel);
        eventValue.put(MoMoParameterNamePayment.MERCHANT_CODE, merchantcode);
        eventValue.put(MoMoParameterNamePayment.AMOUNT, amount);
        eventValue.put(MoMoParameterNamePayment.DESCRIPTION, description);
        eventValue.put("language", language);
        eventValue.put("username", username);
        eventValue.put(MoMoParameterNamePayment.FEE, fee);

        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"ahihi"+data.getStringExtra("data"));
        String dataBack = data.getStringExtra("data");
        String phoneBack = data.getStringExtra("phonenumber");
        String momoappversion = data.getStringExtra("momoappversion");
        String appSource = data.getStringExtra("appSource");
        int status = data.getIntExtra("status",0);
        String message = data.getStringExtra("message");
        String fromapp = data.getStringExtra("fromapp");
        WritableMap params = Arguments.createMap();
        params.putString("data", dataBack);
        params.putString("phonenumber", phoneBack);
        params.putString("momoappversion", momoappversion);
        params.putString("appSource", appSource);
        params.putInt("status", status);
        params.putString("message", message);
        params.putString("fromapp", fromapp);
        sendEvent(getReactInstanceManager().getCurrentReactContext(), "data-call-back-momo", params);
        finish();
    }
    private void sendEvent(ReactContext reactContext, String eventName, WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}
