package com.lifeistech.masakazuozaki.triplebrowser.entity;

import java.io.Serializable;

/**
 * Created by MasakazuOzaki on 16/04/05.
 */
public class Bookmark implements Serializable{
    public String title;
    public String url;

    public Bookmark(String title, String url){
        this.title = title;
        this.url = url;
    }
}
