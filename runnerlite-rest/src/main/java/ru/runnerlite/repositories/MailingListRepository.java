package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.MailingList;
import ru.runnerlite.entities.RefSubscribeType;
import ru.runnerlite.entities.dto.MySubscriptionsDto;

import java.util.List;

@Repository
public interface MailingListRepository extends JpaRepository<MailingList, Integer> {

    @Query(value = "select new ru.runnerlite.entities.dto.MySubscriptionsDto(m.teams.name, m.teams.id, m.refSubscribeType.code, m.refSubscribeType.description) " +
            "from MailingList m where m.secUsers.email = :currentUserName")
    List<MySubscriptionsDto> findMySubscriptions (@Param("currentUserName") String currentUserName);
}