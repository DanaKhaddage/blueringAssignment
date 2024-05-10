package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.ExpensetypeDTO;
import com.bluering.blueringAssignment.Entities.ExpensetypeEntity;
import com.bluering.blueringAssignment.Mappers.ExpenseTypeMapper;
import com.bluering.blueringAssignment.Repositories.ExpenseTypeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {
    @Autowired
    private GeneralService generalService;
    @Autowired
    private ExpenseTypeMapper expenseTypeMapper;
    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    public void createExpenseType(Map<String,Object> expenseTypeDTO){
        ExpensetypeEntity expensetypeEntity =new ExpensetypeEntity();
        generalService.updateEntity(expenseTypeDTO,expensetypeEntity,ExpensetypeEntity.class);
        expenseTypeRepository.saveAndFlush(expensetypeEntity);
    }

    public void deleteExpenseType(Integer id){
        expenseTypeRepository.deleteById(id);
    }

    public List<ExpensetypeDTO> getExpenseTypes(){
        return expenseTypeRepository.findAll()
                .stream()
                .map(expenseTypeMapper::ExpenseTypeEntityToExpenseTypeDTO)
                .collect(Collectors.toList());
    }

    public void updateExpenseType(Integer id,Map<String,Object> expenseTypeDTO){
        ExpensetypeEntity expensetypeEntity=expenseTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense type not found with number: " + id));;
        generalService.updateEntity(expenseTypeDTO,expensetypeEntity,ExpensetypeEntity.class);
        expenseTypeRepository.saveAndFlush(expensetypeEntity);
    }
}
