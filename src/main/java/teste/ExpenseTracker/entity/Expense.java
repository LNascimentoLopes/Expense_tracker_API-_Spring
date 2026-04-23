package teste.ExpenseTracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.util.UUID;

@Entity
@Data
@Table (name = "Expenses")
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Column(length = Length.DEFAULT)
    private String Description;
    @NonNull
    private double price;
    @NonNull
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonProperty("userId")
    public UUID getUserIdForJson() {
        return user != null ? user.getId() : null;
    }


}
