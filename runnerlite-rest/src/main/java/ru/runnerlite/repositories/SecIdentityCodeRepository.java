package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.SecIdentityCode;

@Repository
public interface SecIdentityCodeRepository extends JpaRepository<SecIdentityCode, Integer> {
}