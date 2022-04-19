package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.MailingList;

@Repository
public interface MailingListRepository extends JpaRepository<MailingList, Integer> {
}