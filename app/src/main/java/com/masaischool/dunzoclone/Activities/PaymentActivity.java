package com.masaischool.dunzoclone.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.masaischool.dunzoclone.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity  implements PaymentResultListener {
    int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());
        amount = getIntent().getIntExtra("amount",0);
        startPayment();
    }


    private void startPayment() {
        String  amtStr = Integer.toString(amount*100);
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_RDw06iKDxiNYZu");
        checkout.setImage(R.drawable.rzp_logo);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Abhishek");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amtStr);//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","7777011329");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);
            Log.d("abhishek", "startPayment");
        } catch(Exception e) {
            Log.e("abhishek", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Log.d("abhishek", s);
        redirect();
    }

    private void redirect() {
        startActivity(new Intent(PaymentActivity.this, PaymentSuccess.class));

    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.d("abhishek", s);
    }
}