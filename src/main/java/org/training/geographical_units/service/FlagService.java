package org.training.geographical_units.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.training.geographical_units.dto.FlagResponseDTO;
import org.training.geographical_units.model.Country;
import org.training.geographical_units.model.Flag;
import org.training.geographical_units.repository.FlagRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FlagService {

    private final FlagRepository flagRepository;
    private static final String STORAGE_DIRECTORY = "D:\\Images\\Flags";

    public FlagService(FlagRepository flagRepository) {
        this.flagRepository = flagRepository;
    }

    public File getFlagByName(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException("Filename is null");
        }
        File downloaded = new File(STORAGE_DIRECTORY + File.separator + fileName);
        if (!Objects.equals(downloaded.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Illegal filename");
        }
        if (!downloaded.exists()) {
            throw new FileNotFoundException("No file named: " + fileName);
        }
        return downloaded;
    }

    public FlagResponseDTO getFlagDetails(String name) {
        Flag requested = flagRepository.findByName(name);
        if (requested == null) {
            throw new EntityNotFoundException("Entity with name /" + name + "/ not available!");
        }
        return FlagDTOMapper.toFlagResponseDTO(requested);
    }

    public FlagResponseDTO addFlag(MultipartFile file, int countryId) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File is null");
        }
        File uploaded = new File(STORAGE_DIRECTORY + File.separator + file.getOriginalFilename());
        if (!Objects.equals(uploaded.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Illegal filename");
        }

        Files.copy(file.getInputStream(), uploaded.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Country country = new Country();
        country.setId(countryId);
        return FlagDTOMapper.toFlagResponseDTO(flagRepository
                .save(new Flag(
                        file.getOriginalFilename(),
                        file.getSize(),
                        STORAGE_DIRECTORY,
                        country
        )));
    }

    public void deleteFlagById(int id) {
        Flag flag = flagRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Entity with id /" + id + "/ not available!"
                ));
        flag.getCountry().setFlag(null);
        flagRepository.delete(flag);
    }
}
