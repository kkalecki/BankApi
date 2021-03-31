package pl.kalecki.BankApi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kalecki.BankApi.repository.UserRepository;
import pl.kalecki.BankApi.repository.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;


    public List<User> findAll() {
        return repository.findAll();

    }


    public User save(User user) {

        return repository.save(user);
    }


    public Optional<User> findById(Long aLong) {
        return repository.findById(aLong);
    }


    public void deleteById(Long aLong) {

        repository.deleteById(aLong);

    }
    public List<User> findAllByuserName(String name)
    {
        return repository.findAllByuserName(name);
    }


}
