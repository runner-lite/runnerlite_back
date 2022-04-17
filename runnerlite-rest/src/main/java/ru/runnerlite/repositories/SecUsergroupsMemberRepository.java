package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.SecUsergroupsMember;

@Repository
public interface SecUsergroupsMemberRepository extends JpaRepository<SecUsergroupsMember, Integer> {
}