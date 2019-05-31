package com.example.ejercicio_http;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ejercicio_http.R;

public class DetailActivity  extends AppCompatActivity {

    TextView id, userId, title, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        Intent intent = getIntent();

        if(intent.getExtras() != null){
            id.setText(intent.getStringExtra("ID"));
            userId.setText(intent.getStringExtra("USER_ID"));
            title.setText(intent.getStringExtra("TITLE"));
            body.setText(intent.getStringExtra("BODY"));
        }
    }

    private void init(){
        id = findViewById(R.id.detail_post_id);
        userId = findViewById(R.id.detail_user_id);
        title = findViewById(R.id.detail_title);
        body = findViewById(R.id.detail_body);
    }
}
