package ru.runnerlite.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.SecUser;

import java.util.Optional;

@Repository
public interface SecUserRepository extends JpaRepository<SecUser, Long>  {
	@Override
	@NotNull
	SecUser getById(@NotNull Long id);

	@Query("select distinct su " +
			"from SecUser su " +
			"where su.email = :username ")
	Optional<SecUser> findByUsername(@Param("username") String username);

}