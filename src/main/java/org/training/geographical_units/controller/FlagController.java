package org.training.geographical_units.controller;

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
                    .body(new InputStreamResource(Files.newInputStream(requestedFlag.toPath())));
        }
        catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/unit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addFlag(@RequestPart MultipartFile flag, @RequestPart int countryId) {
        try {
            FlagResponseDTO addedFlag = flagService.addFlag(flag, countryId);
            return new ResponseEntity<>(addedFlag, HttpStatus.CREATED);
        }
        catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/unit")
    public ResponseEntity<String> deleteFlagByName(@RequestParam("flagName") String flagName) {
        flagService.deleteFlagByName(flagName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
