package com.courseproject.travelagencyrestapiawtentication.controllers;


import com.courseproject.travelagencyrestapiawtentication.models.dto.request.CreateReservationDTO;
import com.courseproject.travelagencyrestapiawtentication.models.dto.request.UpdateReservationDTO;
import com.courseproject.travelagencyrestapiawtentication.models.dto.response.ResponseReservationDTO;
import com.courseproject.travelagencyrestapiawtentication.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/user-reservations")
@RestController
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<ResponseReservationDTO> createReservation(@RequestBody CreateReservationDTO reservationDTO){
        ResponseReservationDTO response = service.createReservation(reservationDTO);
        System.out.println("Successfully created Reservation: " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<ResponseReservationDTO>> getAllReservations() {
        System.out.println("The GET statement reservations was called");
        List<ResponseReservationDTO> allReservations = service.getAllReservations();
        return ResponseEntity.ok(allReservations);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ResponseReservationDTO> getReservationById(@PathVariable Long reservationId) {
        ResponseReservationDTO reservation = service.getReservationById(reservationId);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping
    public ResponseEntity<ResponseReservationDTO> updateHoliday(@RequestBody UpdateReservationDTO update) {
        ResponseReservationDTO updatedReservation = service.updateReservation(update);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Boolean> deleteHoliday(@PathVariable Long reservationId) {
        Boolean deleted = service.deleteReservation(reservationId);
        return ResponseEntity.ok(deleted);
    }
}
