package org.training.geographical_units.dto;

import org.training.geographical_units.model.Continent;
import org.training.geographical_units.model.Flag;

public record CountryResponseDTO(
        Integer id,
        String name,
        String capital,
        String continent,
        String flag
) {
}
