package com.junrdev.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.junrdev.news.R;
import com.junrdev.news.model.NewsModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<NewsModel> newsModelList;

    public NewsAdapter(List<NewsModel> newsModels){
        this.newsModelList = newsModels;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notecard, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel _news = newsModelList.get(position);
        holder.setAuthor(_news.getAuthor()== "null" ? " " : _news.getAuthor());
        holder.setTitle(_news.getTitle() == "null" ? "no title" : _news.getTitle());
        holder.setDescription(_news.getDescription() == "null" ? "no description" : _news.getDescription());
        holder.setPublish_date(_news.getPublicationDate() == "null" ? "no date" : _news.getPublicationDate());
    }


    @Override
    public int getItemCount() {
        return newsModelList.size();
    }
}

class NewsViewHolder extends RecyclerView.ViewHolder{

    private TextView title;

    private TextView author;

    private TextView publish_date;

    private TextView description;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.newsTitle);
        author = itemView.findViewById(R.id.newsAuthor);
        publish_date= itemView.findViewById(R.id.publicationDate);
        description= itemView.findViewById(R.id.newsDescription);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public TextView getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.setText(author);
    }

    public TextView getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date.setText(publish_date);
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }
}