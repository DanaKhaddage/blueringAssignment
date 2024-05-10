package com.bluering.blueringAssignment.Mappers;

import com.bluering.blueringAssignment.DTO.ExpenseclaimDTO;
import com.bluering.blueringAssignment.Entities.ExpenseclaimEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ExpenseClaimMapper {
    ExpenseClaimMapper INSTANCE= Mappers.getMapper(ExpenseClaimMapper.class);
    ExpenseclaimDTO ExpenseClaimEntityToExpenseClaimDTO(ExpenseclaimEntity expenseclaimEntity);
    ExpenseclaimEntity ExpenseClaimDTOToExpenseClaimEntity(ExpenseclaimDTO expenseclaimDTO);
}
