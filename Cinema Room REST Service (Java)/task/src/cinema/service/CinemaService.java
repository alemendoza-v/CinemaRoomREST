package cinema.service;

import cinema.exception.OutOfBoundsException;
import cinema.exception.PurchasedTicketException;
import cinema.exception.WrongPasswordException;
import cinema.exception.WrongTokenException;
import cinema.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CinemaService {
    private final Cinema cinema = new Cinema(9, 9);

    public Cinema getSeats() {
        return cinema;
    }

    public PurchaseResponse purchaseTicket(Seat seat) {
        Optional<Seat> optionalSeat = cinema.getAvailable_seats().stream()
                .filter(s -> s.getRow() == seat.getRow())
                .filter(s -> s.getColumn() == seat.getColumn())
                .findFirst();

        if (optionalSeat.isPresent()) {
            Seat existingSeat = optionalSeat.get();
            if (existingSeat.isOccupied()) {
                // Error OCCUPIED
                throw new PurchasedTicketException("The ticket has been already purchased!");
            }
            existingSeat.setOccupied(true);
            String token = UUID.randomUUID().toString();
            cinema.addIsNotReturned(token, existingSeat);
            return new PurchaseResponse(token, existingSeat);
        }
        // Error NOT FOUND
        throw new OutOfBoundsException("The number of a row or a column is out of bounds!");
    }

    public ReturnResponse returnTicket(String token) {
        Seat seat = cinema.getIsNotReturned().getOrDefault(token, null);
        if (seat != null) {
            cinema.deleteIsNotReturned(token);
            seat.setOccupied(false);
            return new ReturnResponse(seat);
        }
        throw new WrongTokenException("Wrong token!");
    }

    public StatsResponse getStats(String password) {
        if (password.equals("super_secret")) {
            int income = 0;
            int purchasedTickets = 0;
            int seats = cinema.getAvailable_seats().size();

            for (Seat seat : cinema.getAvailable_seats()) {
                if (seat.isOccupied()) {
                    income += seat.getPrice();
                    seats--;
                    purchasedTickets++;
                }
            }

            return new StatsResponse(income, seats, purchasedTickets);
        }
        throw new WrongPasswordException("The password is wrong!");
    }
}
