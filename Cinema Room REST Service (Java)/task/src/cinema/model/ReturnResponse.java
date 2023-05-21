package cinema.model;

public class ReturnResponse {
    private Seat returned_ticket;

    public ReturnResponse() {
    }

    public ReturnResponse(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
