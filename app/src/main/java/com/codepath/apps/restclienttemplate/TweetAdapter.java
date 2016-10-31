package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.utils.ConvertDateStamp;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

/**
 * Created by rubit1359 on 10/30/2016.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder>{
    private Context mContext;
    private List<Tweet> mTweets;
    private TweetAdapter adapter;

    // Automatically add the Constructor here

    public TweetAdapter(Context context, List<Tweet> tweets) {
        mContext = context;
        mTweets = tweets;
    }


    // Implements compulsory methods


    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_list_items,
                parent, false);
        TweetViewHolder viewHolder = new TweetViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        Tweet tweet = mTweets.get(position);
        Glide.with(getContext())
                .load(tweet.getUser().getProfileImageUrl())
                .into(holder.userProfile);
        holder.tweetText.setText(tweet.getBody());
        holder.userName.setText(tweet.getUser().getName());
        String timeStamp = ConvertDateStamp.getRelativeTimeAgo(tweet.getCreatedAt());
        holder.timeStamp.setText(timeStamp);


    }

    @Override
    public int getItemCount() {
        return (mTweets !=null) ? mTweets.size() : 0;
    }


    public void setNewTweets(List<Tweet> newTweetList) {
        this.mTweets = newTweetList;
    }

    public class TweetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Hook the ID to new created Widget using ButterKnife
        @BindView(R.id.imgUserProfile)
        ImageView userProfile;
        @BindView(R.id.tvUserName)
        TextView userName;
        @BindView(R.id.tvTweet)
        TextView tweetText;
        @BindView(R.id.tvTimeStamp)
        TextView timeStamp;

        TweetViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            // Add What happen with OnClick



        }
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

}


