package pl.kalecki.BankApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kalecki.BankApi.repository.entity.Account;
import pl.kalecki.BankApi.repository.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Override
    List<Account> findAll();


    Account save(Account account);

    @Override
    Optional<Account> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
