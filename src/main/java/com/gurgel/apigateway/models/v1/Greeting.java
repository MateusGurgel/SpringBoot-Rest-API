package com.gurgel.apigateway.models.v1;

public class Greeting {
    private final long id;
    private final String content;


    public long getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    public Greeting(long id, String content){
        this.id = id;
        this.content = content;
    }

}
