package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
    @Override
    @Query("from Rule order by id")
    Iterable<Rule> findAll();
}
