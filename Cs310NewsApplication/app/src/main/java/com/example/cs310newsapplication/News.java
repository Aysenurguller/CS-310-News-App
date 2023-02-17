package com.example.cs310newsapplication;

import java.util.Date;

public class News {
    /*
    "id": 21,
            "title": "New Zealand PM Ardern caught name-calling rival on hot mic",
            "text": "\nWELLINGTON, New Zealand (AP) — New Zealand Prime Minister Jacinda Ardern was caught on a hot mic Tuesday using a vulgarity against a rival politician in a rare misstep for a leader known for her skill at debating and calm, measured responses.",
            "date": "2022-12-15T21:00:00.000+00:00",
            "image": "http://10.3.0.14:8080/newsapp/images/news21.png",
            "categoryName": "Politics"
     */
    private int id;
    private String title, text;
    private String date;
    private String image, categoryName;

    public News(int id, String title, String text,String date, String image, String categoryName) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.image = image;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
