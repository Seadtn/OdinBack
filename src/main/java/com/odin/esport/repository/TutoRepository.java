package com.odin.esport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.odin.esport.model.Tuto;
@Repository
public interface TutoRepository extends JpaRepository<Tuto, Long> {

}
