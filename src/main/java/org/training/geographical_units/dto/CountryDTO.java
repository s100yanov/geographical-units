package org.training.geographical_units.dto;

import java.util.Date;

public record CountryDTO(
        Integer id,
        String name,
        String capital,
        Date established,
        Long population,
        Double area,
        Integer continentId
) {
}
