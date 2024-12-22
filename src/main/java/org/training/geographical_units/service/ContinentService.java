package org.training.geographical_units.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.training.geographical_units.dto.ContinentDTO;
import org.training.geographical_units.dto.ContinentResponseDTO;
import org.training.geographical_units.model.Continent;
import org.training.geographical_units.repository.ContinentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContinentService {

    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public List<ContinentResponseDTO> getAllContinents() {
        return continentRepository.findAll()
                .stream()
                .map(ContinentDTOMapper::toContinentResponseDto)
                .collect(Collectors.toList());
    }

    public ContinentResponseDTO getContinentById(int id) {
        return continentRepository.findById(id)
                .map(ContinentDTOMapper::toContinentResponseDto)
                .orElse(null);
    }

    public ContinentResponseDTO addContinent(ContinentDTO dto) {
        Continent added = continentRepository.save(ContinentDTOMapper.toContinent(dto));
        return ContinentDTOMapper.toContinentResponseDto(added);
    }

    public ContinentResponseDTO updateContinent(ContinentDTO dto, int id) {
        Continent updated = continentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        ("Entity with id=\"" + id + "\" not available!")
                ));
        updated.setId(dto.id());
        updated.setName(dto.name());
        updated.setPercentageOfWorldLandArea(dto.percentageOfEarthArea());
        return ContinentDTOMapper.toContinentResponseDto(continentRepository.save(updated));
    }

    public void deleteContinent(int id) {
        Continent deleted = continentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        ("Entity with id=\"" + id + "\" not available!")
                ));
        continentRepository.delete(deleted);
    }
}
