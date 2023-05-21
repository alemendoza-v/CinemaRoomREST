package cinema.controller;

import cinema.exception.OutOfBoundsException;
import cinema.exception.PurchasedTicketException;
import cinema.exception.WrongPasswordException;
import cinema.exception.WrongTokenException;
import cinema.model.*;
import cinema.service.CinemaService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("")
public class CinemaController {
    @Autowired
    CinemaService cinemaService;

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getSeats() {
        Cinema cinema = cinemaService.getSeats();
        return new ResponseEntity<>(cinema, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> purchaseTicket(@RequestBody Seat seat) {
        PurchaseResponse result = cinemaService.purchaseTicket(seat);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnResponse> returnTicket(@RequestBody Token token) {
        return new ResponseEntity<>(cinemaService.returnTicket(token.getToken()), HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<StatsResponse> getStats(@RequestParam(name = "password", defaultValue = "") String password) {
        return new ResponseEntity<>(cinemaService.getStats(password), HttpStatus.OK);
    }

    @ExceptionHandler(OutOfBoundsException.class)
    public ResponseEntity<Map<String, String>> handleOutOfBoundsException() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PurchasedTicketException.class)
    public ResponseEntity<Map<String, String>> handlePurchaseTicketException() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "The ticket has been already purchased!");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<Map<String, String>> handleWrongTokenException() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "Wrong token!");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Map<String, String>> handleWrongPasswordException() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "The password is wrong!");
        return new ResponseEntity<>(errorMap, HttpStatus.UNAUTHORIZED);
    }

}
