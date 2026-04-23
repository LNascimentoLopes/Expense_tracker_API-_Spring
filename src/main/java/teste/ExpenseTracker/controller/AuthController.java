package teste.ExpenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teste.ExpenseTracker.dto.LoginRequest;
import teste.ExpenseTracker.dto.RegisterRequest;
import teste.ExpenseTracker.repository.UserRepository;
import teste.ExpenseTracker.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    @Autowired
    private UserRepository repository;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest request){
        return service.login(request.getUsername(), request.getPassword());
    }
    @PostMapping("register")
    public void register(@RequestBody RegisterRequest register){
        try {
            service.register(register);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
