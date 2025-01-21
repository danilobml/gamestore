package com.danilobml.gamestore.projections;

public interface GameMinProjection {
    
    long getId();
    String getTitle();
    int getYear();
    String getImgUrl();
    String getShortDescription();
    int getPosition();

}
