package com.example.sunny.test.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sunny.test.Model.Item;
import com.example.sunny.test.R;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }

    private Gson gson;
    private SharedPreferences sp;
    private Item item;
    private TextView textInfoTitle;
    private TextView textInfoDate;
    private TextView textInfoContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_info, container, false);

        // get the views from layout(xml)
        textInfoTitle = root.findViewById(R.id.textInfoTitle);
        textInfoDate = root.findViewById(R.id.textInfoDate);
        textInfoContent = root.findViewById(R.id.textInfoContent);

        // create gson object
        gson = new Gson();
        // get the shared preference
        sp = getActivity().getSharedPreferences("item", Context.MODE_PRIVATE);
        // get the item string from the shared preference
        String stringItem = sp.getString("item", "");
        // convert the item string to item object
        item = gson.fromJson(stringItem, Item.class);

        // set the views with data
        textInfoTitle.setText(item.getTitle());
        textInfoDate.setText(item.getPubDate());
        textInfoContent.setText(android.text.Html.fromHtml(item.getContent()).toString());

        // create the button on click event
        root.findViewById(R.id.buttonLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send the user to matching link
                Intent goToLink = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                startActivity(goToLink);
            }
        });
        return root;
    }

}
