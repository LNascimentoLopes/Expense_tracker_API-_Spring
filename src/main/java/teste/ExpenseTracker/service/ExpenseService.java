package teste.ExpenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teste.ExpenseTracker.Details.CustomUserDetails;
import teste.ExpenseTracker.dto.ExpenseRequest;
import teste.ExpenseTracker.dto.PatchExpenseRequest;
import teste.ExpenseTracker.entity.Expense;
import teste.ExpenseTracker.entity.User;
import teste.ExpenseTracker.repository.ExpenseRepository;
import teste.ExpenseTracker.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    private UserRepository repositoryUser;
    @Autowired
    private ExpenseRepository expenseRepository;

    public void CreateExpense(ExpenseRequest request, CustomUserDetails user){
        Expense expense = new Expense();
        User currentuser = repositoryUser.getReferenceById(user.getUser().getId());
        expense.setUser(currentuser);
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setPrice(request.getPrice());
        expenseRepository.save(expense);
    }

    public List<Expense> GetAllExpenses(CustomUserDetails user){
        User Current = user.getUser();
        return expenseRepository.findByUserId(Current.getId());

    }

    public void patchExpense(PatchExpenseRequest request, CustomUserDetails user, UUID id )throws Exception {
        User Current = user.getUser();
        Expense expense = expenseRepository.findById(id).orElseThrow();
        if (!Current.getId().equals(expense.getUser().getId())){
            throw new Exception();
        }
        request.getAmount().ifPresent(expense::setAmount);
        request.getDescription().ifPresent(expense::setDescription);
        request.getPrice().ifPresent(expense::setPrice);

        expenseRepository.save(expense);
    }

    public void deleteExpense(UUID id, CustomUserDetails user) throws Exception {
        User Current = user.getUser();

        Expense expense = expenseRepository.findById(id).orElseThrow();

        if (!Current.getId().equals(expense.getUser().getId())){
            throw new Exception();
        }

        expenseRepository.deleteById(expense.getId());
    }
}
