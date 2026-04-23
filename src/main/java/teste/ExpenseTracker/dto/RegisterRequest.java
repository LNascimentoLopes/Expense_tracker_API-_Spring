package teste.ExpenseTracker.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import teste.ExpenseTracker.Enums.Role;
import teste.ExpenseTracker.entity.User;

import java.beans.Encoder;

@Data
public class RegisterRequest {

    private String username;
    private String password;

}
