package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rubit1359 on 10/30/2016.
 */
public class Tweet {
    private long uid;
    private User user;
    private String createdAt;
    private String body;
    private String retweetCount;
    private String favoriteCount;
    private boolean favorited;
    private boolean retweeted;

    public Tweet(JSONObject tweetJson) {
    }


    // Add data to Java Object from JSON

    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet(jsonObject);
        try {
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.uid = jsonObject.getLong("id");
            tweet.body = jsonObject.getString("text");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.retweetCount = jsonObject.getString("retweet_count");
            tweet.favoriteCount = jsonObject.getString("favorite_count");
            tweet.favorited = jsonObject.getBoolean("favorited");
            tweet.retweeted = jsonObject.getBoolean("retweeted");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }


    // Convert JSON Array to Java Array
    public static List<Tweet> fromJsonArray(JSONArray jsonArray) {
        List<Tweet> tweets = new ArrayList<>();
        //Iterate the json array and create tweets
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject tweetJsonObject = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJsonObject);
                if (tweet != null)
                    tweets.add(tweet);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return tweets;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public String getRetweetCount() {
        return retweetCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void save() {

    }
}
