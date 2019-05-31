package com.example.ejercicio_http;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Posts {
    private List<Post> posts;

    public Posts(){
        this.posts = new ArrayList<>();
    }

    public Posts(List<Post> posts){
        this.posts = posts;
    }

    public List<Post> getPosts(){
        return posts;
    }

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Post OneFromJSON(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Post.class);
    }

    public Posts ManyFromJSON(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Posts.class);
    }

    public Posts fromArrayJSON(String json){
        Gson gson = new Gson();
        List<Post> posts =  Arrays.asList(gson.fromJson(json,Post[].class));
        return new Posts(posts);
    }

    public void addPost(Post post){
        posts.add(post);
    }

    public void removePost(Post post){
        posts.remove(post);
    }

    @Override
    public String toString() {
        return "Posts{" +
                "posts=" + posts +
                '}';
    }
}
