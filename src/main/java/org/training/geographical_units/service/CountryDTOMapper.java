package org.training.geographical_units.service;

import org.training.geographical_units.dto.CountryDTO;
import org.training.geographical_units.dto.CountryResponseDTO;
import org.training.geographical_units.model.Continent;
import org.training.geographical_units.model.Country;
import org.training.geographical_units.model.Flag;

public class CountryDTOMapper {

    static CountryResponseDTO toCountryResponseDto(Country country) {
        Continent continent = country.getContinent();
        Flag flag = country.getFlag();
        String missingEntity = "Not available";
        return new CountryResponseDTO(
                country.getId(),
                country.getName(),
                country.getCapital(),
                continent != null ? continent.toString() : missingEntity,
                flag != null ? flag.toString() : missingEntity
        );
    }

    static Country toCountry(CountryDTO dto) {
        Continent continent = new Continent();
        continent.setId(dto.continentId());
        return new Country(
                dto.id(),
                dto.name(),
                dto.capital(),
                dto.established(),
                dto.population(),
                dto.area(),
                continent
        );
    }
}
