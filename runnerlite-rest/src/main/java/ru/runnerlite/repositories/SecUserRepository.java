package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.SecUser;

@Repository
public interface SecUserRepository extends JpaRepository<SecUser, Long> {
	
	SecUser findById(Integer id);
}