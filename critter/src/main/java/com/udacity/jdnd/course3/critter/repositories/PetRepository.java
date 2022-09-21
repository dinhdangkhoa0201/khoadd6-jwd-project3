package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.PetEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {
    List<PetEntity> findByIdIn(List<Long> ids);

    @Query("SELECT p FROM PetEntity p WHERE p.customer.id = :ownerId")
    List<PetEntity> findByOwner(@Param("ownerId") Long ownerId);
}
