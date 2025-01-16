package org.training.geographical_units.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.geographical_units.dto.CountryDTO;
import org.training.geographical_units.dto.CountryResponseDTO;
import org.training.geographical_units.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/home/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CountryResponseDTO>> getAllCountries() {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<CountryResponseDTO> getCountryById(@PathVariable int id) {
        try {
            CountryResponseDTO requestedCountry = countryService.getCountryById(id);
            return new ResponseEntity<>(requestedCountry, HttpStatus.FOUND);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                            HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();
        }
    }

    @GetMapping("/unit/name")
    public ResponseEntity<CountryResponseDTO> getCountryByName(@RequestParam String countryName) {
        try {
            CountryResponseDTO requestedCountry = countryService.getCountryByName(countryName);
            return new ResponseEntity<>(requestedCountry, HttpStatus.FOUND);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                            HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();

        }
    }

    @PostMapping("/unit")
    public ResponseEntity<CountryResponseDTO> addCountry(@RequestBody CountryDTO dto) {
        return new ResponseEntity<>(countryService.addCountry(dto), HttpStatus.CREATED);
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<CountryResponseDTO> updateCountry(@RequestBody CountryDTO dto,@PathVariable int id) {
        try {
            CountryResponseDTO updatedCountry = countryService.updateCountry(dto, id);
            return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                            HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable int id) {
        try {
            countryService.deleteCountry(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
