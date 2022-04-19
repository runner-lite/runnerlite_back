package ru.runnerlite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.EmergencyContact;

@Repository
public interface EmergencyContactRepository extends CrudRepository<EmergencyContact, Long> {
}