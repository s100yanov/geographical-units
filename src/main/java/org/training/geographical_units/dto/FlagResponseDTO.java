package org.training.geographical_units.dto;

public record FlagResponseDTO(
        Integer id,
        String name,
        Long size,
        String pathToStorage
) {
}