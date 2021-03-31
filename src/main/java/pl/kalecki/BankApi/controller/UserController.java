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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@Data
@RequestMapping("/api/v1")
public class UserController {
    private final UserService service;

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest)
    {
        User user = User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .build();
        User savedUser = service.save(user);

        UserResponse userResponse = UserResponse.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .accounts(savedUser.getAccounts())
                .build();
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

    }
    @GetMapping("/users")
    public List<UserResponse> showUsers()
    {
        List<User> all = service.findAll();
        List<UserResponse> userResponses = all.stream().map(user -> UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .accounts(user.getAccounts())
                .build())
                .collect(Collectors.toList());
        return userResponses;

    }
    @GetMapping("/user")
    public UserResponse showUser(@RequestParam Long id)
    {
        User user = service.findById(id).orElseThrow(() -> new IllegalArgumentException("User with this id does not exists"));
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .accounts(user.getAccounts())
                .build();
        return userResponse;
    }
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam Long id)
    {
        service.deleteById(id);
    }

    @GetMapping("/username")
    public List<UserResponse> findAllByuserName(@RequestParam String name)
    {
        List<User> allByuserName = service.findAllByuserName(name);
        List<UserResponse> userResponses = allByuserName.stream().map(user -> UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .accounts(user.getAccounts())
                .build())
                .collect(Collectors.toList());
        return userResponses;
    }



}
