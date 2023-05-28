package com.junrdev.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.junrdev.news.adapter.NewsAdapter;
import com.junrdev.news.databinding.ActivityMainBinding;
import com.junrdev.news.model.NewsModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final String apikey = "&apiKey=220fd65e064342a893d98e887cb22f8c";
    private final String defaultUrl = "https://newsapi.org/v2/everything?q=";
    private ActivityMainBinding binding;

    private ProgressDialog progressDialog;

    private RecyclerView newsRecyclerView;

    private List<NewsModel> allNews = new ArrayList<>();

    private TextView homeAppLogo;

    private String[] random_categories = new String[]{
            "bitcoin",
            "tesla",
            "apple"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        newsRecyclerView = findViewById(R.id.newsView);
        homeAppLogo = binding.newsAppLogo;

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading please wait....");
        progressDialog.show();

        String topic=getRandomTopic();

        Toast.makeText(this, "Fetching about "+ topic, Toast.LENGTH_SHORT).show();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(defaultUrl+topic+apikey).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //&& new JSONObject(response.body().string()).get("status") == "ok"
                if (response.isSuccessful() && response.code() == 200) {
                    Log.d("Resp", "onResponse: code" + response.code());

                    String response_str = "" + response.body().string();
                    Log.d("Resp", "onResponse: " + response_str);

//                    System.out.println(response_str);

                    try {
                        JSONObject response_object = new JSONObject(response_str);

                        JSONArray articles = response_object.getJSONArray("articles");

                        int k=0,i;

                        Log.d("Resp", "onResponse: "+articles.length());

                        while(k< articles.length()){

                            for (i = 0; i < 100; i++, k++) {

                                JSONObject _article_json = articles.getJSONObject(k);

                                allNews.add(
                                        new NewsModel(
                                                _article_json.getString("title"),
                                                _article_json.getString("publishedAt"),
                                                _article_json.getString("author"),
                                                _article_json.getString("description"),
                                                _article_json.getString("content"),
                                                _article_json.getString("url"),
                                                _article_json.getString("urlToImage")
                                        ));

                            }
                            k=i;
                        }

                        if (!allNews.isEmpty()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    newsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                                    newsRecyclerView.setAdapter(new NewsAdapter(allNews));
                                    progressDialog.dismiss();
                                    homeAppLogo.setVisibility(View.GONE);
                                }
                            });

                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });


        binding.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(binding.searchText.getText())) {
//                    Toast.makeText(MainActivity.this, " " + binding.searchText.getText(), Toast.LENGTH_SHORT).show();
                    progressDialog.show();
                } else
                    Toast.makeText(MainActivity.this, "Please add search text", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String getRandomTopic(){
        return random_categories[new Random().nextInt() % random_categories.length];
    }
    private List<NewsModel> getNews() {

        List<NewsModel> _temp = new ArrayList<>();
        _temp.add(new NewsModel("Title 1", "" + new Date(System.currentTimeMillis()).toString(), "Brian Kidiga", "This is a test News Item", "Hello world", "google.com", "google.com/img.png"));
        _temp.add(new NewsModel("Title 1", "" + new Date(System.currentTimeMillis()).toString(), "Brian Kidiga", "This is a test News Item", "Hello world", "google.com", "google.com/img.png"));
        _temp.add(new NewsModel("Title 1", "" + new Date(System.currentTimeMillis()).toString(), "Brian Kidiga", "This is a test News Item", "Hello world", "google.com", "google.com/img.png"));
        _temp.add(new NewsModel("Title 1", "" + new Date(System.currentTimeMillis()).toString(), "Brian Kidiga", "This is a test News Item", "Hello world", "google.com", "google.com/img.png"));
        _temp.add(new NewsModel("Title 1", "" + new Date(System.currentTimeMillis()).toString(), "Brian Kidiga", "This is a test News Item", "Hello world", "google.com", "google.com/img.png"));
        _temp.add(new NewsModel("Title 1", "" + new Date(System.currentTimeMillis()).toString(), "Brian Kidiga", "This is a test News Item", "Hello world", "google.com", "google.com/img.png"));
        return _temp;
    }

}