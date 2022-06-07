package ru.runnerlite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.dto.SecUserDto;

import java.util.Optional;

@Repository
public interface SecUserRepository extends JpaRepository<SecUser, Long>  {


	@Query("select distinct su " +
			"from SecUser su " +
			"join fetch su.secGroup " +
			"where su.email = :username ")
	Optional<SecUser> findByUsername(@Param("username") String username);

	@Query("select su.id " +
			"from SecUser su " +
			"where su.email = :username ")
	Integer findById(@Param("username") String username);

	@Query("select su.team.id " +
			"from SecUser su " +
			"where su.email = :username ")
	Optional<Integer> findTeamByUsername(@Param("username") String username);

	@Query(value = "select new ru.runnerlite.entities.dto.SecUserDto(su.id, su.email, su.fullName, su.nickName, su.useNick) " +
			"from SecUser su " +
			"where su.email=:email")
	SecUserDto findByEmail(@Param("email") String email); //для получения имени и id user'а

}