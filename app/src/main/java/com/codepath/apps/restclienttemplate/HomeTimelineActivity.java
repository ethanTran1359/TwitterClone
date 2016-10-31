package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.utils.ComposeActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class HomeTimelineActivity extends AppCompatActivity {
    private TwitterClient client;
    Context mContext;
    @BindView(R.id.rvTweets)
    RecyclerView rvTweets;
    @BindView(R.id.fbtnWriteTweet)
    FloatingActionButton btnTest;
    List<Tweet> mTweets;
    TweetAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_timeline);
        ButterKnife.bind(this);



        client = TwitterApplication.getRestClient();

        // Initially pass null as the mTweets since you don't have any tweets right now...
        adapter = new TweetAdapter(mContext, null);
        rvTweets.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);

        rvTweets.setLayoutManager(layoutManager);
        populateTimeline();

    }

    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                Log.d("DEBUG", jsonArray.toString());

                mTweets = new ArrayList<Tweet>();

                for (int i = 0; i < jsonArray.length(); i++) {

                        mTweets.addAll(Tweet.fromJsonArray(jsonArray));
                        adapter.setNewTweets(mTweets);
                    Log.d("error","abcde");
                        adapter.notifyDataSetChanged();
                    }
                }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("DEBUT", errorResponse.toString());
            }
        });
    }

    private List<Tweet> loadResponse(List<Tweet> tweets) {
        return tweets;
    }

    public void writeTweet(View view) {
        Intent intent = new Intent(this, ComposeActivity.class);
        startActivity(intent);
    }

}


//}