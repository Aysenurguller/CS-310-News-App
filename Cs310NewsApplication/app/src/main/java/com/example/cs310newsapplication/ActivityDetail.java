package com.example.cs310newsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ActivityDetail extends AppCompatActivity {
    Toolbar toolbar;
    Button btncomment;
    TextView mtitle, mtext, mdate;
    ImageView mimage;




    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            News news = (News) msg.obj;

            mtitle.setText(news.getTitle());
            mdate.setText(news.getDate());
            mtext.setText(news.getText());


            NewsRepository repo = new NewsRepository();
            repo.downloadImage(((CS310NewsApp)getApplication()).srv,imgHandler,news.getImage());

            return true;
        }
    });
    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Bitmap img = (Bitmap)msg.obj;
            mimage.setImageBitmap(img);

            return true;
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        int id = getIntent().getIntExtra("id",1);


        toolbar=findViewById(R.id.idToolbar);
        btncomment=findViewById(R.id.btnComment);
        mtext=findViewById(R.id.textDetail);
        mtitle=findViewById(R.id.titleDetail);
        mdate=findViewById(R.id.dateDetail);
        mimage = findViewById(R.id.imageDetail);


        NewsRepository repo = new NewsRepository();
        repo.getDataById(((CS310NewsApp)getApplication()).srv,dataHandler,id);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return true;
    }

   
}