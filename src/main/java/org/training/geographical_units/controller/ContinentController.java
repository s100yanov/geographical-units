package org.training.geographical_units.controller;

import org.springframework.http.HttpStatus;
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
        ContinentResponseDTO requestedContinent = continentService.getContinentById(id);
        if (requestedContinent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requestedContinent, HttpStatus.FOUND);
    }

    @PostMapping("/unit")
    public ResponseEntity<ContinentResponseDTO> addContinent(@RequestBody ContinentDTO dto) {
        return new ResponseEntity<>(continentService.addContinent(dto), HttpStatus.CREATED);
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<?> updateContinent(@RequestBody ContinentDTO dto, @PathVariable int id) {
        return new ResponseEntity<>(continentService.updateContinent(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<String> deleteContinent(@PathVariable int id) {
        continentService.deleteContinent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
