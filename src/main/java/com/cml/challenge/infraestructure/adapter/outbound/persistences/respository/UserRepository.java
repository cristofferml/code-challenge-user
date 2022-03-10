package com.cml.challenge.infraestructure.adapter.outbound.persistences.respository;


import com.cml.challenge.infraestructure.adapter.outbound.persistences.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  UserEntity findByUsername(String Username);

}
