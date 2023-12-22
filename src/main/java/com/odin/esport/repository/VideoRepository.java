package com.odin.esport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odin.esport.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	List<Video>findVideoByUserId(Long iduser);
}
