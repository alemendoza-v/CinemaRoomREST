package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Cinema {
    // private long id;
    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats = new ArrayList<>();
    @JsonIgnore
    private Map<String, Seat> isNotReturned = new HashMap<>();

    public Cinema() {
    }

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                available_seats.add(new Seat(i, j));
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public Map<String, Seat> getIsNotReturned() {
        return isNotReturned;
    }

    public void setIsNotReturned(Map<String, Seat> isNotReturned) {
        this.isNotReturned = isNotReturned;
    }

    public void addIsNotReturned(String token, Seat seat) {
        this.isNotReturned.put(token, seat);
    }

    public void deleteIsNotReturned(String token) {
        this.isNotReturned.remove(token);
    }
}
