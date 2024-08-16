package com.courseproject.travelagencyrestapiawtentication.controllers;


import com.courseproject.travelagencyrestapiawtentication.models.dto.request.CreateHolidayDTO;
import com.courseproject.travelagencyrestapiawtentication.models.dto.request.UpdateHolidayDTO;
import com.courseproject.travelagencyrestapiawtentication.models.dto.response.ResponseHolidayDTO;
import com.courseproject.travelagencyrestapiawtentication.service.HolidayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holidays")
@CrossOrigin(origins = "*")
public class HolidayController {

    private final HolidayService holidayService;
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }


   @PostMapping
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<ResponseHolidayDTO> createLocation(@RequestBody CreateHolidayDTO createHolidayDTO) {
       System.out.println(createHolidayDTO);
       ResponseHolidayDTO responseHolidayDTO = holidayService.createHoliday(createHolidayDTO);
       System.out.println("Successfully created Holiday");
       return
               ResponseEntity.status(HttpStatus.CREATED).body(responseHolidayDTO);
   }

    // GET vsi`ki
    @GetMapping
    public ResponseEntity<List<ResponseHolidayDTO>> getAllHolidays() {
        List<ResponseHolidayDTO> allHolidays = holidayService.getAllHolidays();
        return ResponseEntity.ok(allHolidays);
    }

    // GET by ID - Open to all users
    @GetMapping("/{holidayId}")
    public ResponseEntity<ResponseHolidayDTO> getHolidayById(@PathVariable Long holidayId) {
        ResponseHolidayDTO holiday = holidayService.getHolidayById(holidayId);
        return ResponseEntity.ok(holiday);
    }

    // PUT - Requires ADMIN role
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseHolidayDTO> updateHoliday(@RequestBody UpdateHolidayDTO updateHolidayDTO) {
        ResponseHolidayDTO updatedHoliday = holidayService.updateHoliday(updateHolidayDTO);
        return ResponseEntity.ok(updatedHoliday);
    }

    // DELETE - Requires ADMIN role
    @DeleteMapping("/{holidayId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteHoliday(@PathVariable Long holidayId) {
        Boolean deleted = holidayService.deleteHoliday(holidayId);
        return ResponseEntity.ok(deleted);
    }
}
