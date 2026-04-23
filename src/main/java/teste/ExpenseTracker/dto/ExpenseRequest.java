package teste.ExpenseTracker.dto;

import lombok.Data;

@Data
public class ExpenseRequest {

    private String description;
    private double price;
    private int amount;

}
