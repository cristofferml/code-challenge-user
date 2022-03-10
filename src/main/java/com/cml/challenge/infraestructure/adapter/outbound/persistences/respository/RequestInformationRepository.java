package com.cml.challenge.infraestructure.adapter.outbound.persistences.respository;


import com.cml.challenge.infraestructure.adapter.outbound.persistences.entities.RequestInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestInformationRepository extends JpaRepository<RequestInformationEntity, Integer> {


}
