package com.odin.esport.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.odin.esport.model.Agent;
import com.odin.esport.model.Camps;
import com.odin.esport.model.Footballer;
import com.odin.esport.model.Poste;
import com.odin.esport.model.Tuto;
import com.odin.esport.model.User;
import com.odin.esport.repository.AgentRepository;
import com.odin.esport.repository.CampsRepository;
import com.odin.esport.repository.CommentRepository;
import com.odin.esport.repository.FootballerRepository;
import com.odin.esport.repository.PosteRepository;
import com.odin.esport.repository.TutoRepository;
import com.odin.esport.repository.UserRepository;

import io.jsonwebtoken.io.IOException;

@Controller
@RequestMapping("/Admin/values")
public class AdminController {
	@Autowired
	AgentRepository agRep;
	@Autowired
	FootballerRepository footRep;
	@Autowired
	UserRepository userRep;
	@Autowired
	PosteRepository posteRep;
	@Autowired
	CommentRepository commentRep;
	@Autowired
	CampsRepository campsRep;
	@Autowired
	TutoRepository tutoRep;
	private byte[] UploadImage(MultipartFile file) throws java.io.IOException {
		byte[] fileContent = null;
		try {
			fileContent = file.getBytes();

		} catch (IOException e) {

		}
		return fileContent;

	}

	@PostMapping("/addPoste")
	public ResponseEntity<Poste> AddPoste(@RequestPart("posteImage") MultipartFile posteImage,
			@RequestPart("Description") String desc,
			@RequestPart("date") String date) throws java.io.IOException {
		byte[] PosteImage = UploadImage(posteImage);
		Poste p = new Poste(desc, PosteImage,date);
		Poste savedPoste = posteRep.save(p);
		if (savedPoste == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPoste);
	}
	@PostMapping("/addTuto")
	public ResponseEntity<Tuto> AddTuto(@RequestPart("iframe") String iframe,
			@RequestPart("titre") String titre,
			@RequestPart("desc") String desc) throws java.io.IOException {
		if (iframe.isEmpty() || titre.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(tutoRep.save(new Tuto(iframe,titre,desc)));
	}

	@PostMapping("/addCamp")
	public ResponseEntity<Camps> AddCamp(@RequestPart("campImage") MultipartFile campImage,
			@RequestPart("details") String details,
			@RequestPart("title") String title,
			@RequestPart("location") String location) throws java.io.IOException {
		byte[] image = UploadImage(campImage);
		Camps	c = new Camps(image, title,location,details);
		Camps savedCamps = campsRep.save(c);
		if (savedCamps == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCamps);
	}
	@PostMapping("/UpdateState")
	public ResponseEntity<User> UpdateState(
			@RequestParam("iduser") Long iduser,
			@RequestParam("state") boolean state,
			@RequestParam("type") String type
			) {
		User u = null;
		if(type.equals("Footballer")) {
			Footballer f=(Footballer)userRep.findUserById(iduser);
			if(state==false) {f.setLicense(null);f.setVerified(false);}
			u=f;
		}else if(type.equals("Agent")) {
			Agent a=(Agent)userRep.findUserById(iduser);
			if(state==false) {a.setDeals(null);a.setVerified(false);}
			u=a;
		}
		if(state==true) {
			u.setVerified(state);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(userRep.save(u));
	}
	/*@PostMapping("/UpdateState")
	public ResponseEntity<User> UpdateState(){
		System.out.println("adzdazdazazda");
		return null;
	}*/
}
