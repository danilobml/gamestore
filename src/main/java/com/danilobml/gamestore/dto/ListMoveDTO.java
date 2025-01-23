package com.danilobml.gamestore.dto;

public class ListMoveDTO {
    
    private int originIndex;
    private int destinationIndex; 

    public ListMoveDTO(int originIndex, int destinationIndex) {
        this.originIndex = originIndex;
        this.destinationIndex = destinationIndex;
    }

    public int getOriginIndex() {
        return this.originIndex;
    }

    public void setOriginIndex(int originIndex) {
        this.originIndex = originIndex;
    }

    public int getDestinationIndex() {
        return this.destinationIndex;
    }

    public void setDestinationIndex(int destinationIndex) {
        this.destinationIndex = destinationIndex;
    }

}
