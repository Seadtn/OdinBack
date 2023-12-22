package com.odin.esport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odin.esport.model.Reagir;

public interface ReagirRepository extends JpaRepository<Reagir, Long> {
	 Reagir findReagirByUserIdAndPosteId(long iduser,long idposte);
	 List<Reagir> findReagirByPosteId(long idposte);
}
