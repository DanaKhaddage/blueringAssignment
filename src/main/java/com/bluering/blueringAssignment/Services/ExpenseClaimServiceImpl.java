package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.ExpenseclaimDTO;
import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;
import com.bluering.blueringAssignment.DTO.ExpensetypeDTO;
import com.bluering.blueringAssignment.Entities.ExpenseclaimEntity;
import com.bluering.blueringAssignment.Entities.ExpensetypeEntity;
import com.bluering.blueringAssignment.Mappers.ExpenseClaimMapper;
import com.bluering.blueringAssignment.Mappers.ExpenseTypeMapper;
import com.bluering.blueringAssignment.Repositories.ExpenseClaimRepository;
import com.bluering.blueringAssignment.Repositories.ExpenseTypeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseClaimServiceImpl implements ExpenseClaimService{
    @Autowired
    private GeneralService generalService;
    @Autowired
    private ExpenseClaimMapper expenseClaimMapper;
    @Autowired
    private ExpenseClaimRepository expenseClaimRepository;

    private double calculateTotal(List<ExpenseclaimentryDTO> expenseClaimEntries){
        double totalAmount = 0.0;
        for(ExpenseclaimentryDTO expenseclaimentryDTO : expenseClaimEntries){
            totalAmount += expenseclaimentryDTO.getTotal();
        }
        return totalAmount;
    }

    public void createExpenseClaim(ExpenseclaimDTO expenseClaimDTO){
        ExpenseclaimEntity expenseClaimEntity =expenseClaimMapper.ExpenseClaimDTOToExpenseClaimEntity(expenseClaimDTO);
        double totalAmount=calculateTotal(expenseClaimDTO.getExpenseClaimEntry());
        expenseClaimEntity.setTotal(totalAmount);
    }

    public void deleteExpenseClaim(Integer id){
        expenseClaimRepository.deleteById(id);
    }

    public List<ExpenseclaimDTO> getExpenseClaims(){
        return expenseClaimRepository.findAll()
                .stream()
                .map(expenseClaimMapper::ExpenseClaimEntityToExpenseClaimDTO)
                .collect(Collectors.toList());
    }

    public void updateExpenseClaim(Integer id,Map<String,Object> expenseClaimDTO){
        ExpenseclaimEntity expenseclaimEntity=expenseClaimRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense claim not found with number: " + id));;
        generalService.updateEntity(expenseClaimDTO,expenseclaimEntity,ExpenseclaimEntity.class);
        expenseClaimRepository.saveAndFlush(expenseclaimEntity);
    }
}
