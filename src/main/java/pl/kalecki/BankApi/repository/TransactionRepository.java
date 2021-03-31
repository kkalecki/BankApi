package pl.kalecki.BankApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kalecki.BankApi.repository.entity.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Override
    List<Transaction> findAll();

    @Override
    Transaction save(Transaction transaction);

    @Override
    Optional<Transaction> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
