package teste.ExpenseTracker.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Data
public class PatchExpenseRequest {

    private Optional<String> description = Optional.empty();
    private Optional<Double> price = Optional.empty();
    private Optional<Integer> amount = Optional.empty();


}
