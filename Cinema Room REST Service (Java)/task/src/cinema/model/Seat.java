package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    // private long id;
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean occupied;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        if (row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
        this.occupied = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
