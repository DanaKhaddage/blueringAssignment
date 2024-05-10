package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.DepartmentDTO;
import com.bluering.blueringAssignment.DTO.EmployeeDTO;
import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;
import com.bluering.blueringAssignment.DTO.ExpensetypeDTO;
import com.bluering.blueringAssignment.Entities.DepartmentEntity;
import com.bluering.blueringAssignment.Entities.EmployeeEntity;
import com.bluering.blueringAssignment.Entities.ExpenseclaimentryEntity;
import com.bluering.blueringAssignment.Mappers.*;
import com.bluering.blueringAssignment.Repositories.DepartmentRepository;
import com.bluering.blueringAssignment.Repositories.EmployeeRepository;
import com.bluering.blueringAssignment.Repositories.ExpenseClaimEntryRepository;
import com.bluering.blueringAssignment.Repositories.ExpenseTypeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseClaimEntryServiceImpl implements ExpenseClaimEntryService {
    @Autowired
    private GeneralService generalService;
    @Autowired
    private ExpenseClaimEntryMapper expenseClaimEntryMapper;
    @Autowired
    private ExpenseClaimEntryRepository expenseClaimEntryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ExpenseTypeMapper expenseTypeMapper;
    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    public void createExpenseClaimEntry(Map<String,Object> expenseClaimEntryDTO){
        ExpenseclaimentryEntity expenseclaimentryEntity=new ExpenseclaimentryEntity();
        generalService.updateEntity(expenseClaimEntryDTO,expenseclaimentryEntity,ExpenseclaimentryEntity.class);
        expenseClaimEntryRepository.saveAndFlush(expenseclaimentryEntity);
    }

    public void deleteExpenseClaimEntry(Integer id){
        expenseClaimEntryRepository.deleteById(id);
    }

    public List<ExpenseclaimentryDTO> getExpenseClaimEntries(){
        return expenseClaimEntryRepository.findAll()
                .stream()
                .map(expenseClaimEntryMapper::ExpenseClaimEntryEntityToExpenseClaimEntryDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getExpenseClaimsPerEmployeePerType(Integer id){
        List<EmployeeEntity> employees=employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs=new ArrayList<>();
        for(EmployeeEntity employee:employees){
            EmployeeDTO employeeDTO=employeeMapper.EmployeeEntityToEmployeeDTO(employee);
            List<Integer> expenseTypes=expenseClaimEntryRepository.getExpensesForEmployee(employee.getId());
            List<ExpensetypeDTO> expensetypeDTOS=new ArrayList<>();
            for(Integer expenseType:expenseTypes){
                ExpensetypeDTO expensetypeDTO=expenseTypeMapper
                        .ExpenseTypeEntityToExpenseTypeDTO(expenseTypeRepository.getReferenceById(expenseType));
                expensetypeDTO.setExpenseClaimEntries(expenseClaimEntryRepository.findByExpenseType(expenseType)
                        .stream()
                        .map(expenseClaimEntryMapper::ExpenseClaimEntryEntityToExpenseClaimEntryDTO)
                        .collect(Collectors.toList()));
                expensetypeDTOS.add(expensetypeDTO);
            }
            employeeDTO.setExpenseTypeDTOList(expensetypeDTOS);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    public void updateExpenseClaimEntry(Integer id,Map<String,Object> expenseClaimEntryDTO){
        ExpenseclaimentryEntity expenseclaimentryEntity=expenseClaimEntryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense Claim Entry not found with number: " + id));;
        generalService.updateEntity(expenseClaimEntryDTO,expenseclaimentryEntity,ExpenseclaimentryEntity.class);
        expenseClaimEntryRepository.saveAndFlush(expenseclaimentryEntity);
    }
}
