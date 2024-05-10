package com.bluering.blueringAssignment.Mappers;

import com.bluering.blueringAssignment.DTO.ExpensetypeDTO;
import com.bluering.blueringAssignment.Entities.ExpensetypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ExpenseTypeMapper {
    ExpenseTypeMapper INSTANCE= Mappers.getMapper(ExpenseTypeMapper.class);
    ExpensetypeDTO ExpenseTypeEntityToExpenseTypeDTO(ExpensetypeEntity expensetypeEntity);
    ExpensetypeEntity ExpenseTypeDTOToExpenseTypeEntity(ExpensetypeDTO expensetypeDTO);
}
