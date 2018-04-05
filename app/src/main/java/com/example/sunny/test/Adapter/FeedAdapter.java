package com.example.sunny.test.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sunny.test.Interface.ItemClickListener;
import com.example.sunny.test.MainActivity;
import com.example.sunny.test.Model.Item;
import com.example.sunny.test.Model.RSSObject;
import com.example.sunny.test.R;
import com.google.gson.Gson;

/**
 * Created by Sunny on 12/03/2018.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder>{

    private RSSObject rssObject;
    private LayoutInflater inflater;
    private Context context;
    private SharedPreferences sp;
    private Gson gson;

    // constructor
    public FeedAdapter(RSSObject rssObject, Context context) {
        this.rssObject = rssObject;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row, parent, false);
        // get the SharedPreference
        sp = context.getSharedPreferences("item", Context.MODE_PRIVATE);
        // create gson object
        gson = new Gson();
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {

        // set the views with the data
        holder.textTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.textPubDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.textContent.setText(android.text.Html.fromHtml(rssObject.getItems().get(position).getContent()).toString());
        holder.bind(rssObject.getItems().get(position));
    }

    @Override
    public int getItemCount() {
        if (rssObject != null) {
            // get the list size
            return rssObject.items.size();
        }
        return 0;
    }

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textTitle;
    public TextView textPubDate;
    public TextView textContent;
    private Item item;

    private ItemClickListener itemClickListener;

    public FeedViewHolder(View itemView) {
        super(itemView);

        // connect to views from the layout
        textTitle = itemView.findViewById(R.id.textTitle);
        textPubDate = itemView.findViewById(R.id.textPubDate);
        textContent = itemView.findViewById(R.id.textContent);

        // create item click event
        itemView.setOnClickListener(this);
    }

//    public void setItemClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }

    // get the current item
    public void bind(Item item) {
        this.item = item;
    }

    // on click event
    @Override
    public void onClick(View view) {

        // convert the item to gson
        String stringItem = gson.toJson(item);
        // use the SharedPreferences to share the gson place
        sp.edit().putString("item", stringItem).commit();
        // use the MainActivity method to change to InfoFragment
        ((MainActivity) context).openInfoFrag();
    }
}
}
