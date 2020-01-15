package com.example.newsapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<NewsData> mDataset;
    private static View.OnClickListener onClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public SimpleDraweeView ImageView_title;
        public TextView TextView_title;
        public TextView TextView_content;
        public View rootView;

        public MyViewHolder(View v) {
            super(v);
            TextView_title = v.findViewById(R.id.TextView_title); // 부모에서 id를 찾아야 하기에 .findViewById
            TextView_content = v.findViewById(R.id.TextView_content);
            ImageView_title = (SimpleDraweeView) v.findViewById(R.id.ImageView_title);
            rootView = v;

            v.setClickable(true);
            v.setEnabled(true);
            v.setOnClickListener(onClickListener); // 어디를 눌러도 넘어가게끔
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset, Context context, View.OnClickListener onClick) { //card로 보여줄 초기데이터셋, context를 넘기면 메모리 누수가 발생함
        Fresco.initialize(context);
        mDataset = myDataset;
        onClickListener = onClick;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        NewsData news = mDataset.get(position);

        holder.TextView_title.setText(news.getTitle());
        String content = news.getContent();
        if(content != "null" && content.length() > 0) {
            holder.TextView_content.setText(content);
        }
        else {
            holder.TextView_content.setText("-");
        }
        Uri uri = Uri.parse(news.getUrlToImage());
        holder.ImageView_title.setImageURI(uri);

        // tag - label
        holder.rootView.setTag(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset ==null? 0: mDataset.size();
    }

    public NewsData getNews(int position) {
        return mDataset == null? null : mDataset.get(position);
    }
}
