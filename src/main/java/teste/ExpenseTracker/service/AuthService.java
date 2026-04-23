package teste.ExpenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teste.ExpenseTracker.Details.CustomUserDetails;
import teste.ExpenseTracker.dto.Mapper;
import teste.ExpenseTracker.dto.RegisterRequest;
import teste.ExpenseTracker.entity.User;
import teste.ExpenseTracker.repository.UserRepository;

import java.util.List;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public AuthService(AuthenticationManager authManager, JwtService jwtService, PasswordEncoder encoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }
    public String login (String username, String password) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
        } catch (Exception e){
            throw e;
        }
        User user = repository.findByUsername(username);
        UserDetails usd = new CustomUserDetails(user);
        return jwtService.generateToken(usd);
    }
    public void register (RegisterRequest request) throws Exception {

        if (repository.findByUsername(request.getUsername())!= null){
            throw new Exception("already exists");
        }

        User user = new Mapper().dtoToEntity(request,encoder);

        repository.save(user);


    }
}
