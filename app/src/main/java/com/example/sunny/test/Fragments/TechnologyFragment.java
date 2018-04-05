package com.example.sunny.test.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sunny.test.Adapter.FeedAdapter;
import com.example.sunny.test.AsyncTask.LoadRssAsync;
import com.example.sunny.test.MainActivity;
import com.example.sunny.test.Model.RSSObject;
import com.example.sunny.test.R;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class TechnologyFragment extends Fragment {


    public TechnologyFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RSSObject rssObject;
    private ProgressDialog progress;


    private final String RSS_LINK = "http://www.ynet.co.il/Integration/StoryRss545.xml";
    private final String RSS_TO_JSON_API = "https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_technology, container, false);

        recyclerView = root.findViewById(R.id.techList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadRSS();

        return root;
    }

    private void loadRSS() {

        // create string builder object and insert the mako link and api
        final StringBuilder urlGetData = new StringBuilder(RSS_TO_JSON_API);
        urlGetData.append(RSS_LINK);

        progress = ProgressDialog.show(getContext(), getString(R.string.dialog_title),
                getString(R.string.dialog_message), true);

        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    // send the string builder to LoadRssAsync method and get the result
                    String result = new LoadRssAsync((MainActivity)getContext()).execute(urlGetData.toString()).get();
                    // convert the result(string) to RSSObject
                    rssObject = new Gson().fromJson(result, RSSObject.class);
                    Log.i("", "runnable try working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        progress.dismiss();
                        Log.i("", "runnable not try working");
                        // create adapter object and send the data
                        FeedAdapter adapter = new FeedAdapter(rssObject, getContext());
                        // connect between the adapter and xml list
                        recyclerView.setAdapter(adapter);
                        // update the changes
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

}
