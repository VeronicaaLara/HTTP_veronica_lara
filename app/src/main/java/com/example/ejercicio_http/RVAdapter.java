package com.example.ejercicio_http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PostViewHolder>{
    public class PostViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView id, userId, title, body;

        PostViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.cv);

            id = itemView.findViewById(R.id.post_id);
            userId = itemView.findViewById(R.id.user_id);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }

    List<Post> posts;
    Context context;
    private CustomItemClick customItemClick;

    public RVAdapter(Context context, List<Post> posts, CustomItemClick customItemClick){
        this.context = context;
        this.posts = posts;
        this.customItemClick = customItemClick;
    }

    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.card, viewGroup, false);
        final PostViewHolder pvh = new PostViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemClick.onClick(v, pvh.getAdapterPosition());
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        postViewHolder.id.setText(String.valueOf(posts.get(i).getId()));
        postViewHolder.userId.setText(String.valueOf(posts.get(i).getUserId()));
        postViewHolder.title.setText(posts.get(i).getTitle());
        postViewHolder.body.setText(posts.get(i).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
