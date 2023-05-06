package com.example.newshub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newshub.model.News;
import com.example.newshub.utility.NetworkUtility;
import com.example.newshub.utility.NewsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<News> data;
    private RecyclerView newsView;
    private NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        String[] texts={"skjdfhshf","kjasdhfkjhf"};
        NetworkUtility.createConnection(texts, new NetworkUtility.networkCallback() {
            @Override
            public void onDataFetched(ArrayList<News> list) {
                 data=list;
            }
        });
        newsView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        newsView.setAdapter(adapter);
    }
    private void init(){
        newsView= (RecyclerView) findViewById(R.id.news_view);
        adapter=new NewsAdapter(data,getApplicationContext());
    }
}