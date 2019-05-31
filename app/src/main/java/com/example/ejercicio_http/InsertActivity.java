package com.example.ejercicio_http;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ejercicio_http.R;

public class InsertActivity  extends AppCompatActivity {

    Button btnPost;
    EditText userId, title, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
    }

    private void init(){
        btnPost = findViewById(R.id.btn_post);
        userId = findViewById(R.id.newUserId_et);
        title = findViewById(R.id.newTitle_et);
        body = findViewById(R.id.newBody_et);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] array = new String[3];
                array[0] = userId.getText().toString().trim();
                array[1] = title.getText().toString().trim();
                array[2] = title.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("POST", array);
                setResult( 2, intent);
                finish();
            }
        });
    }
}
