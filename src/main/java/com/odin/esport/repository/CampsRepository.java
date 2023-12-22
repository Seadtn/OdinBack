package com.odin.esport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.odin.esport.model.Camps;

@Repository
public interface CampsRepository extends JpaRepository<Camps, Long> {

}
