package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.SecIdentity;

@Repository
public interface SecIdentityRepository extends JpaRepository<SecIdentity, Integer> {
}