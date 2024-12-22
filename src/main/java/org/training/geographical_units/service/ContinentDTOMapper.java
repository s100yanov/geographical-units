package org.training.geographical_units.service;

import org.training.geographical_units.dto.ContinentDTO;
import org.training.geographical_units.dto.ContinentResponseDTO;
import org.training.geographical_units.model.Continent;
import org.training.geographical_units.model.Country;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ContinentDTOMapper {

    static ContinentResponseDTO toContinentResponseDto(Continent continent) {
        return new ContinentResponseDTO(
                continent.getId(),
                continent.getName(),
                continent.getCountries()
                        .stream()
                        .map(Country::toString)
                        .collect(Collectors.toList())
        );
    }

    static Continent toContinent(ContinentDTO dto) {
        return new Continent(
                dto.id(),
                dto.name(),
                dto.percentageOfEarthArea(),
                new ArrayList<>());
    }
}
