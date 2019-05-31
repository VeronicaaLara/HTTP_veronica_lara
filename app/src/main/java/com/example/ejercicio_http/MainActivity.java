package com.example.ejercicio_http;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements  AsyncResponse, View.OnClickListener {

    RecyclerView rview;
    RVAdapter adapter;
    Posts posts;
    HttpAsync httpAsync;
    Button btnGet, btnGetParam, btnPostActivity;
    EditText postId;
    int btn_clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        rview = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rview.setLayoutManager(llm);
        rview.setHasFixedSize(true);
        posts = new Posts();
        adapter = new RVAdapter(MainActivity.this, posts.getPosts(), new CustomItemClick() {
            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("ID", String.valueOf(posts.getPosts().get(i).getId()));
                intent.putExtra("USER_ID", String.valueOf(posts.getPosts().get(i).getUserId()));
                intent.putExtra("TITLE", posts.getPosts().get(i).getTitle());
                intent.putExtra("BODY", posts.getPosts().get(i).getBody());
                startActivity(intent);
            }
        });

        rview.setAdapter(adapter);
        btnGet = findViewById(R.id.btn_get);
        btnGetParam = findViewById(R.id.btn_getParam);
        btnPostActivity = findViewById(R.id.btn_postActivity);
        postId = findViewById(R.id.paramID_et);
        btnGet.setOnClickListener(this);
        btnGetParam.setOnClickListener(this);
        btnPostActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        btn_clicked = v.getId();
        httpAsync = new HttpAsync();

        switch (v.getId()) {
            case R.id.btn_get:
                ExecuteHttpGet();
                break;
            case R.id.btn_getParam:
                ExecuteHttpGetParam();
                break;
            case R.id.btn_postActivity:
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivityForResult(intent, 2);
                break;
        }
        httpAsync.delegate = (AsyncResponse) MainActivity.this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            String[] message = data.getStringArrayExtra("POST");
            if (message != null) {
                ExecuteHttpPost(message);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.err_msg_empty_post), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ExecuteHttpGet() {
        httpAsync.execute(MainActivity.this, "get", "");
    }

    private void ExecuteHttpGetParam() {
        String id = postId.getText().toString().trim();

        if (!id.isEmpty()) {
            httpAsync.execute(MainActivity.this, "getparam", id);
        }
    }

    private void ExecuteHttpPost(String[] array) {
        if (!array[0].isEmpty() || !array[1].isEmpty() || !array[2].isEmpty()) {
            httpAsync.execute(MainActivity.this, "post", array);
        }
    }

    public void processFinish(Object output) {
        String json = output.toString();

        if (!json.isEmpty()) {
            posts.getPosts().clear();
            if (btn_clicked == R.id.btn_get)
                posts.getPosts().addAll(posts.fromArrayJSON(json).getPosts());
            else {
                posts.getPosts().add(posts.OneFromJSON(json));
            }
        }
        this.adapter.notifyDataSetChanged();
    }
}