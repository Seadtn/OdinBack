package com.odin.esport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odin.esport.model.Image;

public interface ImagesRepository extends JpaRepository<Image, Long> {
	List<Image>findImageByUserId(Long iduser);
}
