package com.junrdev.news;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.imageview.ShapeableImageView;
import com.junrdev.news.databinding.ActivitySingleNoteViewBinding;

import java.util.Objects;

public class SingleNoteView extends AppCompatActivity {

    private ActivitySingleNoteViewBinding binding;

    private TextView singleNewsTitle, singleNewsDescription, singleNewsContent, singleNewsAuthor, singleNewsDate;


    private ImageView imagePlaceHolder;

    private ShapeableImageView openInBrowserImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySingleNoteViewBinding.inflate(getLayoutInflater());

//        setContentView(R.layout.activity_single_note_view);
        setContentView(binding.getRoot());

        singleNewsTitle = binding.singleNoteTitle;
        singleNewsDescription = binding.singleNewsDescription;
        singleNewsContent = binding.singleNewsContent;
        imagePlaceHolder = binding.imagePlaceHolder;
        singleNewsAuthor = binding.singleNewsAuthor;
        singleNewsDate = binding.singleNewsDate;

        openInBrowserImageView = binding.openInBrowserImageView;

        if(!getIntent().getStringExtra("title").isEmpty()){

            String title = getIntent().getStringExtra("title");
            String author = getIntent().getStringExtra("author");
            String publication_date = getIntent().getStringExtra("publication_date");
            String description = getIntent().getStringExtra("description");
            String content = getIntent().getStringExtra("content");
            String url = getIntent().getStringExtra("url");
            String url_to_image = getIntent().getStringExtra("url_to_image");

            singleNewsTitle.setText(title);
            singleNewsDescription.setText(description);
            singleNewsContent.setText(content);
            singleNewsAuthor.setText(author);
            singleNewsDate.setText(publication_date);

            //load image using glide
            runOnUiThread(()-> {
                Glide.with(this).load(url_to_image).into(imagePlaceHolder);
            });
//            Toast.makeText(this, " url "+url_to_image, Toast.LENGTH_SHORT).show();


            //openning the news link inside a browser
            openInBrowserImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent _browser_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(_browser_intent);
                }
            });
        }


    }
}