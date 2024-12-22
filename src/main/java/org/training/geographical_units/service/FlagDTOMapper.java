package org.training.geographical_units.service;

import org.training.geographical_units.dto.FlagResponseDTO;
import org.training.geographical_units.model.Flag;

public class FlagDTOMapper {

    static FlagResponseDTO toFlagResponseDTO (Flag flag) {
        return new FlagResponseDTO(
                flag.getId(),
                flag.getName(),
                flag.getSize(),
                flag.getPath()
        );
    }
}
