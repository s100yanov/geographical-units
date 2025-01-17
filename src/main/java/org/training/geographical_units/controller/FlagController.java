package org.training.geographical_units.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.training.geographical_units.dto.FlagResponseDTO;
import org.training.geographical_units.service.FlagService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/home/flag")
public class FlagController {

    private final FlagService flagService;

    public FlagController(FlagService flagService) {
        this.flagService = flagService;
    }

    @GetMapping("/unit")
    public ResponseEntity<Resource> getFlagByName(@RequestParam("flagName") String flagName) {
        try {
            File requestedFlag = flagService.getFlagByName(flagName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; flagName=\"" + flagName + "\"")
                    .contentLength(requestedFlag.length())
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(Files.newInputStream(requestedFlag.toPath()
                    )));
        }
        catch (IOException e) {
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();
        }
    }

    @GetMapping("/unit/details")
    public ResponseEntity<FlagResponseDTO> getFlagDetails(@RequestParam("flagName") String flagName) {
        try {
            FlagResponseDTO requestedFlag = flagService.getFlagDetails(flagName);
            return new ResponseEntity<>(requestedFlag, HttpStatus.FOUND);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                            HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();
        }
    }

    @PostMapping(value = "/unit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FlagResponseDTO> addFlag(@RequestPart MultipartFile flag, @RequestPart int countryId) {
        try {
            FlagResponseDTO addedFlag = flagService.addFlag(flag, countryId);
            return new ResponseEntity<>(addedFlag, HttpStatus.CREATED);
        }
        catch (IOException e) {
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }

    @DeleteMapping("/unit/remove-and-detach/{id}")
    public ResponseEntity<String> removeDBDetailsAndDetachFromParent(@PathVariable int id) {
        try {
            flagService.deleteFlagById(id);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
