package com.codepath.apps.restclienttemplate.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.HomeTimelineActivity;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApplication;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    private TwitterClient mTwitterClient;
    private Button btnTweet;

    @BindView(R.id.edtTweet)
    EditText edtTweet;
//    @BindView(R.id.btnTweet)
//    Button btnTweet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        ButterKnife.bind(this);
        mTwitterClient = TwitterApplication.getRestClient();


    }




    public void tweetText(View view) {
        mTwitterClient.composeTweet(edtTweet.getText().toString(), new
                JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Intent intent = getIntent();
                        intent.putExtra("status", true);
                        setResult(RESULT_OK, intent);
                        Log.d("error","abcde");
                        finish();
                        Intent i = new Intent(ComposeActivity.this, HomeTimelineActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Log.d("error compose",errorResponse.toString());
                    }
                });
    }
}
