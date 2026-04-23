package teste.ExpenseTracker.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teste.ExpenseTracker.Details.CustomUserDetails;
import teste.ExpenseTracker.dto.ExpenseRequest;
import teste.ExpenseTracker.dto.PatchExpenseRequest;
import teste.ExpenseTracker.entity.Expense;

import teste.ExpenseTracker.service.ExpenseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService es;

    @PostMapping
    public ResponseEntity CreateExpense(@RequestBody ExpenseRequest request, @AuthenticationPrincipal CustomUserDetails user){
       es.CreateExpense(request,user);
       return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }
    @GetMapping
    public List<Expense> ListAllExpenses(@AuthenticationPrincipal CustomUserDetails user){
        return es.GetAllExpenses(user);
    }

    @PatchMapping
    public ResponseEntity PatchExpense(@RequestBody PatchExpenseRequest peRequest,@PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails user){
        try {
            es.patchExpense(peRequest,user, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inexistent or Invalid parameters");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Updated");
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity DeleteExpense (@PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails user){
        try {
            es.deleteExpense(id,user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inexistent or Invalid parameters");
        }
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }


}
