package com.example.getStartedExercise.getstartedexercise.repository.model;

import javax.validation.constraints.NotEmpty;

/**
 * Article
 */
public class Article {
	
	private int id;
    @NotEmpty(message = "hahahaha ban aiii")
	private String title;
    private String author = "HAKA";
    private String description =" MAKO";
    private String thumbnail ="hehe.png" ;

    public Article() {
        this.id =0;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }/**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    public Article(int id) {
        this.id = id ;
        this.title = "Harry Potter";
    }

    public Article (int id ,String title){
        this.id = id;
        this.title =title;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article : {id = "+id +"title"+title+"thumbnail"+thumbnail+"}";
    }
}