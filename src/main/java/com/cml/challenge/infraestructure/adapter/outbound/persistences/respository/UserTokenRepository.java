package com.cml.challenge.infraestructure.adapter.outbound.persistences.respository;


import com.cml.challenge.infraestructure.adapter.outbound.persistences.entities.UserTokenEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Integer> {

  Optional<UserTokenEntity> findByUsername(String Username);

}
