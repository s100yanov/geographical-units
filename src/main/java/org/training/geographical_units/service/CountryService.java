package org.training.geographical_units.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.training.geographical_units.dto.CountryDTO;
import org.training.geographical_units.dto.CountryResponseDTO;
import org.training.geographical_units.model.Continent;
import org.training.geographical_units.model.Country;
import org.training.geographical_units.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryResponseDTO> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryDTOMapper::toCountryResponseDto)
                .collect(Collectors.toList());
    }

    public CountryResponseDTO getCountryById(int id) {
        return countryRepository.findById(id)
                .map(CountryDTOMapper::toCountryResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Entity with id /" + id + "/ not available!"
                ));
    }

    public CountryResponseDTO getCountryByName(String name) {
        Country requested = countryRepository.findByName(name);
        if (requested == null) {
            throw new EntityNotFoundException("Entity with name /" + name + "/ not available!");
        }
        return CountryDTOMapper.toCountryResponseDto(requested);
    }

    public CountryResponseDTO addCountry(CountryDTO dto) {
        Country added = countryRepository.save(CountryDTOMapper.toCountry(dto));
        return CountryDTOMapper.toCountryResponseDto(added);
    }

    public CountryResponseDTO updateCountry(CountryDTO dto, int id) {
        Country updated = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Entity with id /" + id + "/ not available!"
                ));
        Continent continent = new Continent();
        continent.setId(dto.continentId());
        updated.setName(dto.name());
        updated.setCapital(dto.capital());
        updated.setEstablishmentDate(dto.established());
        updated.setPopulation(dto.population());
        updated.setAreaInKm2(dto.area());
        updated.setContinent(continent);
        return CountryDTOMapper.toCountryResponseDto(countryRepository.save(updated));
    }

    public void deleteCountry(int id) {
        Country deleted = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Entity with id /" + id + "/ not available!"
                ));
        countryRepository.delete(deleted);
    }
}
