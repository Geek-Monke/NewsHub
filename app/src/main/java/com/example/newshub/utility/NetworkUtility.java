package com.example.newshub.utility;

import com.example.newshub.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtility {
    public interface networkCallback{
        void onDataFetched(ArrayList<News> list);
    }
    public static void createConnection(String[] texts,networkCallback callback){

        //creating HttpClient Instance
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.worldnewsapi.com/search-news?text=tesla&api-key=b548949732f240229903bad198963d20";
        //building the request
        Request request = new Request.Builder()
                .url(url)
                .build();
        //making the call
        //enque method calls the api asynchronously
        client.newCall(request).enqueue(new Callback() {
            //onFailure is called when there is network error
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle network error here
            }
            //onResponse is called when there is success
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();//this is the result
                ArrayList<News> list=scrapJSON(responseBody);
                callback.onDataFetched(list);
            }
        });

    }

    private  static ArrayList<News> scrapJSON(String json) {
        ArrayList<News> list=new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray newsArr = jsonObj.optJSONArray("news");

            for (int i = 0; i < newsArr.length(); i++) {
                JSONObject newsObj = newsArr.optJSONObject(i);
                String text = newsObj.optString("text");
                String title = newsObj.optString("title");
                String imageUrl = newsObj.optString("image");
                list.add(new News(title,text,imageUrl));
                System.out.println("Text: " + text);
                System.out.println("Title: " + title);
                System.out.println("Image URL: " + imageUrl);
            }
        } catch (JSONException e) {

        }
        return list;
    }
}
