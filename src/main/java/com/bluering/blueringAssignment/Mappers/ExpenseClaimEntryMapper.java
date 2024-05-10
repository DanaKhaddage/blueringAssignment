package com.bluering.blueringAssignment.Mappers;

import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;
import com.bluering.blueringAssignment.Entities.ExpenseclaimentryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface ExpenseClaimEntryMapper {
    ExpenseClaimEntryMapper INSTANCE= Mappers.getMapper(ExpenseClaimEntryMapper.class);
    ExpenseclaimentryDTO ExpenseClaimEntryEntityToExpenseClaimEntryDTO(ExpenseclaimentryEntity expenseclaimentryEntity);
    ExpenseclaimentryEntity ExpenseClaimEntryDTOToExpenseClaimEntryEntity(ExpenseclaimentryDTO expenseclaimentryDTO);
}
