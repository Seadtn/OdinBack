package com.odin.esport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.odin.esport.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {
	 Optional<User>findByEmail(String email);
	 Optional<User>findByEmailAndPassword(String email,String password);
	 boolean existsByEmail(String email);
	 User findUserById(Long id_user);
}
