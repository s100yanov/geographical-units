package org.training.geographical_units.dto;

import java.util.List;

public record ContinentResponseDTO(
        Integer id,
        String name,
        List<String> countries
) {
}
