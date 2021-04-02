package pl.kalecki.BankApi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kalecki.BankApi.controller.dto.UserRequest;
import pl.kalecki.BankApi.controller.dto.UserResponse;
import pl.kalecki.BankApi.repository.UserRepository;
import pl.kalecki.BankApi.repository.entity.User;
import pl.kalecki.BankApi.service.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;


    public List<UserResponse> findAll() {

        List<User> all = repository.findAll();
        return mapper.mapUsersToUserResponses(all);

    }


    public UserResponse save(UserRequest userRequest) {

        User user = mapper.mapUserRequestToUser(userRequest);

        User savedUser = repository.save(user);

        return mapper.mapUserToUserResponse(savedUser);
    }


    public UserResponse findById(Long id) {

        User user = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with this id does not exists"));

        return mapper.mapUserToUserResponse(user);
    }


    public void deleteById(Long aLong) {

        repository.deleteById(aLong);

    }

    public List<UserResponse> findAllByuserName(String name) {
        List<User> allByuserName = repository.findAllByuserName(name);

        return mapper.mapUsersToUserResponses(allByuserName);
    }


}
