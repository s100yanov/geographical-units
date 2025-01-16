package org.training.geographical_units.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.geographical_units.dto.ContinentDTO;
import org.training.geographical_units.dto.ContinentResponseDTO;
import org.training.geographical_units.service.ContinentService;

import java.util.List;

@RestController
@RequestMapping("/home/continent")
public class ContinentController {

    private final ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ContinentResponseDTO>> getAllContinents() {
        return new ResponseEntity<>(continentService.getAllContinents(), HttpStatus.OK);
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<ContinentResponseDTO> getContinentById(@PathVariable int id) {
        try {
            ContinentResponseDTO requestedContinent = continentService.getContinentById(id);
            return new ResponseEntity<>(requestedContinent, HttpStatus.FOUND);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                            HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();
        }
    }

    @PostMapping("/unit")
    public ResponseEntity<ContinentResponseDTO> addContinent(@RequestBody ContinentDTO dto) {
        return new ResponseEntity<>(continentService.addContinent(dto), HttpStatus.CREATED);
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<ContinentResponseDTO> updateContinent(@RequestBody ContinentDTO dto, @PathVariable int id) {
        try {
            ContinentResponseDTO updatedContinent = continentService.updateContinent(dto, id);
            return new ResponseEntity<>(updatedContinent, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                            HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<String> deleteContinent(@PathVariable int id) {
        try {
            continentService.deleteContinent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
