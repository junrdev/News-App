package com.junrdev.news.model;

public class NewsModel {

    private String title;

    private String publicationDate;

    private String author;

    private String description;

    private String content;

    private String url;

    private String urlToImage;

    public NewsModel() {
    }

    public NewsModel(String title, String publicationDate, String author, String description, String content, String url, String urlToImage) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.description = description;
        this.content = content;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
