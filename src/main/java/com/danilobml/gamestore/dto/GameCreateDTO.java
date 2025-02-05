package com.danilobml.gamestore.dto;

import com.danilobml.gamestore.validation.Genre;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GameCreateDTO {
 
    @NotBlank(message = "The title cannot be blank")
    private String title;

    @NotNull(message = "The year cannot be null")
    @Min(value = 1970, message = "Year must be after 1970")
    private int year;

    @NotBlank(message = "The genre cannot be blank")
    @Genre
    private String genre;

    @NotBlank(message = "Insert at least one platform, or more in sequence as a string")
    private String platforms;

    @Min(value = 0, message = "The score cannot be less than 0")
    @Max(value = 10, message = "The score cannot be more than 10")
    private double score;

    @NotBlank(message = "The image URL cannot be blank")
    private String imgUrl;

    private String shortDescription;
    private String longDescription;

    public GameCreateDTO() {
    }

    public GameCreateDTO(String title, int year, String genre, String platforms, double score, String imgUrl, String shortDescription, String longDescription) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.platforms = platforms;
        this.score = score;
        this.imgUrl = imgUrl;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatforms() {
        return this.platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

}
