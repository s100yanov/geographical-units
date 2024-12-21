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
        return new ResponseEntity<>(countryService.addCountry(dto), HttpStatus.CREATED);
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<?> updateCountry(@RequestBody CountryDTO dto,@PathVariable int id) {
        return new ResponseEntity<>(countryService.updateCountry(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable int id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
