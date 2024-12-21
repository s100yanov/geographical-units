package org.training.geographical_units.controller;

import org.springframework.http.HttpStatus;
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
        CountryResponseDTO requestedCountry = countryService.getCountryById(id);
        if (requestedCountry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requestedCountry, HttpStatus.FOUND);
    }

    @PostMapping("/unit")
    public ResponseEntity<CountryResponseDTO> addCountry(@RequestBody CountryDTO dto) {
        CountryResponseDTO createdCountry = countryService.addCountry(dto);
        if (createdCountry == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdCountry, HttpStatus.CREATED);
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<CountryResponseDTO> updateCountry(@RequestBody CountryDTO dto,@PathVariable int id) {
        CountryResponseDTO updatedCountry = countryService.updateCountry(dto, id);
        if (updatedCountry == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<CountryResponseDTO> deleteCountry(@PathVariable int id) {
        CountryResponseDTO deleted = countryService.deleteCountry(id);
        if (deleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
