package nl.aegon.calculator.transformer;

import nl.aegon.calculator.model.CalculationModel;
import nl.aegon.calculator.web.dto.CalculationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculationTransformer {
    private final ModelMapper modelMapper;

    CalculationTransformer(ModelMapper mapper) {
        this.modelMapper = mapper;
        this.modelMapper.typeMap(CalculationDTO.class, CalculationModel.class).addMappings(modelMapper -> {
            modelMapper.skip(CalculationModel::setResult);
            modelMapper.skip(CalculationModel::setType);
        });
    }

    /**
     * Maps an entity to a DTO for presentation in front-end
     *
     * @param calculationModel - The input DTO
     * @return The mapped DTO
     */
    public final CalculationDTO toDTO(CalculationModel calculationModel) {
        return modelMapper.map(calculationModel, CalculationDTO.class);
    }

    /**
     * Maps a DTO to an entity for persistence in database
     *
     * @param calculationDTO - The input DTO
     * @return The mapped entity
     */
    public final CalculationModel toModel(CalculationDTO calculationDTO) {
        return modelMapper.map(calculationDTO, CalculationModel.class);
    }
}
