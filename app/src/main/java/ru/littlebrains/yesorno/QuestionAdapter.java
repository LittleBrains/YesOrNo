package ru.littlebrains.yesorno;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.javatechig.widgetdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Evgeny on 22.05.2016.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.CustomViewHolder>  {
    private List<QuestionModel> feedItemList;
    private Context mContext;

    public QuestionAdapter(Context context, List<QuestionModel> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_question, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        QuestionModel feedItem = feedItemList.get(i);

        //Setting text view title
        customViewHolder.question.setText(feedItem.question.length() > 0 ? feedItem.question : "Empty");
        customViewHolder.answer.setText(feedItem.answer == 0 ? "No" : "YES");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date d = new Date();
        d.setTime((long)feedItem.timestamp*1000);
        customViewHolder.timestamp.setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView question;
        protected TextView answer;
        protected TextView timestamp;

        public CustomViewHolder(View view) {
            super(view);
            this.question = (TextView) view.findViewById(R.id.question);
            this.answer = (TextView) view.findViewById(R.id.answer);
            this.timestamp = (TextView) view.findViewById(R.id.timestamp);
        }
    }

}
