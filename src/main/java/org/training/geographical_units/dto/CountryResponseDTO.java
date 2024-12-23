package org.training.geographical_units.dto;

public record CountryResponseDTO(
        Integer id,
        String name,
        String capital,
        String continent,
        String flag
) {
}
