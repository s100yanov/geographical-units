package org.training.geographical_units.dto;

public record CountryDTO(
        Integer id,
        String name,
        String capital,
        String established,
        Long population,
        Double area,
        Integer continentId
) {
}
