package pl.kalecki.BankApi.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kalecki.BankApi.controller.dto.UserRequest;
import pl.kalecki.BankApi.controller.dto.UserResponse;
import pl.kalecki.BankApi.repository.UserRepository;
import pl.kalecki.BankApi.repository.entity.User;
import pl.kalecki.BankApi.service.UserService;
import pl.kalecki.BankApi.service.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@Data
@RequestMapping("/api/v1")
public class UserController {
    private final UserService service;

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        UserResponse userResponse = service.save(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

    }

    @GetMapping("/users")
    public List<UserResponse> showUsers() {
        return service.findAll();

    }

    @GetMapping("/user")
    public UserResponse showUser(@RequestParam Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam Long id) {
        service.deleteById(id);
    }

    @GetMapping("/username")
    public List<UserResponse> findAllByuserName(@RequestParam String name) {

        return service.findAllByuserName(name);
    }


}
