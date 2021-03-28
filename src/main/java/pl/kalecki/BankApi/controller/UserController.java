package pl.kalecki.BankApi.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import pl.kalecki.BankApi.repository.UserRepository;
import pl.kalecki.BankApi.repository.entity.User;
import pl.kalecki.BankApi.service.UserService;

import java.util.List;


@RestController
@Data
@RequestMapping("/api/v1")
public class UserController {
    private final UserService service;

    @PostMapping("/user")
    public User createUser(@RequestBody User user)
    {
        return service.save(user);

    }
    @GetMapping("/users")
    public List<User> showUsers()
    {
        return service.findAll();

    }
    @GetMapping("/user")
    public User showUser(@RequestParam Long id)
    {
        return service.findById(id).orElseThrow(() -> new IllegalArgumentException("User with this id does not exists"));
    }
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam Long id)
    {
        service.deleteById(id);
    }


}
