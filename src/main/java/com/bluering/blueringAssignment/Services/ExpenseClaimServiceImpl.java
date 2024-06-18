package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.ExpenseclaimDTO;
import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;
import com.bluering.blueringAssignment.Entities.ExpenseclaimEntity;
import com.bluering.blueringAssignment.Entities.ExpenseclaimentryEntity;
import com.bluering.blueringAssignment.Mappers.ExpenseClaimEntryMapper;
import com.bluering.blueringAssignment.Mappers.ExpenseClaimMapper;
import com.bluering.blueringAssignment.Repositories.ExpenseClaimEntryRepository;
import com.bluering.blueringAssignment.Repositories.ExpenseClaimRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseClaimServiceImpl implements ExpenseClaimService {

    @Autowired
    private ExpenseClaimMapper expenseClaimMapper;
    @Autowired
    private ExpenseClaimRepository expenseClaimRepository;
    @Autowired
    private ExpenseClaimEntryRepository expenseClaimEntryRepository;
    @Autowired
    private ExpenseClaimEntryMapper expenseClaimEntryMapper;
    @Autowired
    private GeneralService generalService;

    @Override
    public void createExpenseClaim(ExpenseclaimDTO expenseClaimDTO) {
        expenseClaimDTO.recalculateTotal();
        ExpenseclaimEntity expenseClaimEntity = expenseClaimMapper.ExpenseClaimDTOToExpenseClaimEntity(expenseClaimDTO);
        expenseClaimRepository.save(expenseClaimEntity);
    }

    @Override
    public void addExpenseClaimEntry(Integer claimId, ExpenseclaimentryDTO entryDTO) {
        ExpenseclaimEntity expenseClaimEntity = expenseClaimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense claim not found with number: " + claimId));

        ExpenseclaimDTO expenseClaimDTO = expenseClaimMapper.ExpenseClaimEntityToExpenseClaimDTO(expenseClaimEntity);
        expenseClaimDTO.addEntry(entryDTO);

        expenseClaimEntity = expenseClaimMapper.ExpenseClaimDTOToExpenseClaimEntity(expenseClaimDTO);
        expenseClaimRepository.save(expenseClaimEntity);
    }

    @Override
    public void deleteExpenseClaimEntry(Integer claimId, Integer entryId) {
        ExpenseclaimEntity expenseClaimEntity = expenseClaimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense claim not found with number: " + claimId));

        ExpenseclaimDTO expenseClaimDTO = expenseClaimMapper.ExpenseClaimEntityToExpenseClaimDTO(expenseClaimEntity);
        ExpenseclaimentryDTO entryDTO = expenseClaimDTO.getExpenseClaimEntry().stream()
                .filter(entry -> entry.getId().equals(entryId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Expense claim entry not found with number: " + entryId));

        expenseClaimDTO.removeEntry(entryDTO);

        expenseClaimEntity = expenseClaimMapper.ExpenseClaimDTOToExpenseClaimEntity(expenseClaimDTO);
        expenseClaimRepository.save(expenseClaimEntity);
    }

    @Override
    public List<ExpenseclaimDTO> getExpenseClaims() {
        return expenseClaimRepository.findAll()
                .stream()
                .map(expenseClaimMapper::ExpenseClaimEntityToExpenseClaimDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseclaimentryDTO> getEntriesByExpenseClaimId(Integer expenseClaimId) {
        List<ExpenseclaimentryEntity> entries = expenseClaimEntryRepository.findAllByExpenseClaim(expenseClaimId);
        return entries.stream()
                .map(expenseClaimEntryMapper::ExpenseClaimEntryEntityToExpenseClaimEntryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateExpenseClaim(Integer id, Map<String, Object> expenseClaimDTO) {
        ExpenseclaimEntity expenseclaimEntity = expenseClaimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense claim not found with number: " + id));
        generalService.updateEntity(expenseClaimDTO, expenseclaimEntity, ExpenseclaimEntity.class);
        expenseClaimRepository.saveAndFlush(expenseclaimEntity);
    }

    @Override
    public void deleteExpenseClaim(Integer id) {
        expenseClaimRepository.deleteById(id);
    }
}










//package com.bluering.blueringAssignment.Services;
//
//import com.bluering.blueringAssignment.DTO.ExpenseclaimDTO;
//import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;
//import com.bluering.blueringAssignment.DTO.ExpensetypeDTO;
//import com.bluering.blueringAssignment.Entities.ExpenseclaimEntity;
//import com.bluering.blueringAssignment.Entities.ExpenseclaimentryEntity;
//import com.bluering.blueringAssignment.Entities.ExpensetypeEntity;
//import com.bluering.blueringAssignment.Mappers.ExpenseClaimEntryMapper;
//import com.bluering.blueringAssignment.Mappers.ExpenseClaimMapper;
//import com.bluering.blueringAssignment.Mappers.ExpenseTypeMapper;
//import com.bluering.blueringAssignment.Repositories.ExpenseClaimEntryRepository;
//import com.bluering.blueringAssignment.Repositories.ExpenseClaimRepository;
//import com.bluering.blueringAssignment.Repositories.ExpenseTypeRepository;
//import org.apache.velocity.exception.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class ExpenseClaimServiceImpl implements ExpenseClaimService{
//    @Autowired
//    private GeneralService generalService;
//    @Autowired
//    private ExpenseClaimMapper expenseClaimMapper;
//    @Autowired
//    private ExpenseClaimRepository expenseClaimRepository;
//    @Autowired
//    private ExpenseClaimEntryRepository expenseClaimEntryRepository;
//    @Autowired
//    private ExpenseClaimEntryMapper expenseClaimEntryMapper;
//
//    private double calculateTotal(List<ExpenseclaimentryDTO> expenseClaimEntries){
//        double totalAmount = 0.0;
//        for (ExpenseclaimentryDTO expenseclaimentryDTO : expenseClaimEntries) {
//            totalAmount += expenseclaimentryDTO.getTotal();
//        }
//        return totalAmount;
//    }
//
//    public void createExpenseClaim(ExpenseclaimDTO expenseClaimDTO) {
//        ExpenseclaimEntity expenseClaimEntity = expenseClaimMapper.ExpenseClaimDTOToExpenseClaimEntity(expenseClaimDTO);
//
//        List<ExpenseclaimentryDTO> expenseClaimEntries = expenseClaimDTO.getExpenseClaimEntry();
//        double totalAmount = 0.0;
//
//        if (expenseClaimEntries != null) {
//            totalAmount = calculateTotal(expenseClaimEntries);
//        }
//
//        expenseClaimEntity.setTotal(totalAmount);
//
//        // Save the entity after setting the total amount
//        expenseClaimRepository.save(expenseClaimEntity);
//    }
//
////    public void createExpenseClaim(ExpenseclaimDTO expenseClaimDTO) {
////        ExpenseclaimEntity expenseClaimEntity = expenseClaimMapper.ExpenseClaimDTOToExpenseClaimEntity(expenseClaimDTO);
////        double totalAmount = calculateTotal(expenseClaimDTO.getExpenseClaimEntry());
////        expenseClaimEntity.setTotal(totalAmount);
////
////    }
////        ExpenseclaimEntity expenseclaimEntity=new ExpenseclaimEntity();
////        generalService.updateEntity(expenseClaimDTO,expenseclaimEntity,ExpenseclaimEntity.class);
////        expenseClaimRepository.saveAndFlush(expenseclaimEntity);
//
//    public void deleteExpenseClaim(Integer id){
//        expenseClaimRepository.deleteById(id);
//    }
//
//    public List<ExpenseclaimDTO> getExpenseClaims(){
//        return expenseClaimRepository.findAll()
//                .stream()
//                .map(expenseClaimMapper::ExpenseClaimEntityToExpenseClaimDTO)
//                .collect(Collectors.toList());
//    }
//
//    public List<ExpenseclaimentryDTO> getEntriesByExpenseClaimId(Integer expenseClaimId) {
//        List<ExpenseclaimentryEntity> entries = expenseClaimEntryRepository.findAllByExpenseClaim(expenseClaimId);
//        return entries.stream()
//                .map(entry -> {
//                    ExpenseclaimentryDTO entryDTO = expenseClaimEntryMapper.ExpenseClaimEntryEntityToExpenseClaimEntryDTO(entry);
//                    return entryDTO;
//                })
//                .collect(Collectors.toList());
//    }
//
//    public void updateExpenseClaim(Integer id,Map<String,Object> expenseClaimDTO){
//        ExpenseclaimEntity expenseclaimEntity=expenseClaimRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense claim not found with number: " + id));;
//        generalService.updateEntity(expenseClaimDTO,expenseclaimEntity,ExpenseclaimEntity.class);
//        expenseClaimRepository.saveAndFlush(expenseclaimEntity);
//    }
//}
