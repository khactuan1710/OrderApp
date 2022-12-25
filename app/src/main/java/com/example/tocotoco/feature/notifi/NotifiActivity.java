package com.example.tocotoco.feature.notifi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tocotoco.R;

public class NotifiActivity extends AppCompatActivity {

    TextView tv_title, tv_content;
    ImageView img_notifi;
    private Intent intent;
    String title, content, image;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi);
        intent = getIntent();
        title = intent.getStringExtra("titleNotifi");
        content = intent.getStringExtra("contentNotifi");
        image = intent.getStringExtra("imageNotifi");
        tv_title = findViewById(R.id.tv_title_notifi);
        tv_content = findViewById(R.id.tv_content);
        img_notifi = findViewById(R.id.img_notifi);
        if(!title.isEmpty()) {
            tv_title.setText(title);
        }
        if(!content.isEmpty()) {
            tv_content.setText(content);
        }
        if(!image.isEmpty()) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);



            Glide.with(this).load(image).apply(options).into(img_notifi);
        }

    }
}