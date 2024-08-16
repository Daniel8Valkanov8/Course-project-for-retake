package com.courseproject.travelagencyrestapiawtentication.controllers;

import com.courseproject.travelagencyrestapiawtentication.models.dto.request.CreateLocationDTO;
import com.courseproject.travelagencyrestapiawtentication.models.dto.request.UpdateLocationDTO;
import com.courseproject.travelagencyrestapiawtentication.models.dto.response.ResponseLocationDTO;
import com.courseproject.travelagencyrestapiawtentication.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins = "*")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ResponseLocationDTO>> getAllLocations() {
        List<ResponseLocationDTO> allLocations = locationService.getAllLocations();
        for (ResponseLocationDTO allLocation : allLocations) {
            System.out.println(allLocation);
        }
        return ResponseEntity.ok(allLocations);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseLocationDTO> createLocation(@RequestBody CreateLocationDTO createLocationDTO) {
        ResponseLocationDTO responseLocationDTO = locationService.createLocation(createLocationDTO);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(responseLocationDTO);
    }

    @DeleteMapping("/{locationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteLocation(@PathVariable Long locationId) {

        Boolean deleted = locationService.deleteLocation(locationId);
        if (deleted) System.out.println("Success deleted location with id: " + locationId);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/{locationId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseLocationDTO> getLocationById(@PathVariable Long locationId) {
        ResponseLocationDTO location = locationService.getLocationById(locationId);
        return ResponseEntity.ok(location);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseLocationDTO> updateLocation(@RequestBody UpdateLocationDTO updateLocationDTO) {
        ResponseLocationDTO updatedLocation = locationService.updateLocation(updateLocationDTO);
        return ResponseEntity.ok(updatedLocation);
    }
}
