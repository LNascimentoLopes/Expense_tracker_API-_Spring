package teste.ExpenseTracker.dto;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import teste.ExpenseTracker.Enums.Role;
import teste.ExpenseTracker.entity.User;

@Component
public class Mapper {

    public User dtoToEntity(RegisterRequest request, PasswordEncoder encoder){
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        return user;

    }

}
