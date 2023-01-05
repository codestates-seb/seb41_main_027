package main027.server.domain.place.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@Validated
public class PlaceController {

    @PostMapping
    public ResponseEntity postPlace() {
        return ResponseEntity.created(null).build();
    }

    @PatchMapping("/{placeId}")
    public ResponseEntity patchPlace() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity getPlace() {
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity getPlaces() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity deletePlace() {
        return ResponseEntity.noContent().build();
    }
}
