package com.odin.esport.controller;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odin.esport.exception.ResourceNotFound;
import com.odin.esport.model.Agent;
import com.odin.esport.model.AuthentificationRequest;
import com.odin.esport.model.Camps;
import com.odin.esport.model.Comment;
import com.odin.esport.model.Footballer;
import com.odin.esport.model.Image;
import com.odin.esport.model.Poste;
import com.odin.esport.model.Reagir;
import com.odin.esport.model.RegisterRequest;
import com.odin.esport.model.Tuto;
import com.odin.esport.model.User;
import com.odin.esport.model.UserRole;
import com.odin.esport.model.Video;
import com.odin.esport.repository.AgentRepository;
import com.odin.esport.repository.CampsRepository;
import com.odin.esport.repository.CommentRepository;
import com.odin.esport.repository.FootballerRepository;
import com.odin.esport.repository.ImagesRepository;
import com.odin.esport.repository.PosteRepository;
import com.odin.esport.repository.ReagirRepository;
import com.odin.esport.repository.TutoRepository;
import com.odin.esport.repository.UserRepository;
import com.odin.esport.repository.VideoRepository;

import io.jsonwebtoken.io.IOException;

@Controller
@RequestMapping("/api/values")
public class AppController {
	@Autowired
	AgentRepository agRep;
	@Autowired
	FootballerRepository footRep;
	@Autowired
	UserRepository userRep;
	@Autowired
	PosteRepository postRep;
	@Autowired
	CampsRepository campsRep;
	@Autowired
	TutoRepository tutoRep;
	@Autowired
	CommentRepository commentRep;
	@Autowired
	ReagirRepository reagirRep;
	@Autowired
	VideoRepository videoRep;
	@Autowired
	ImagesRepository imageRep;
	BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

	@PostMapping("/login")
	public ResponseEntity<User> getUserLogin(@RequestBody AuthentificationRequest userData) {
		User user = userRep.findByEmail(userData.getEmail())
				.orElseThrow(() -> new ResourceNotFound("User doesn't exist ! "));
		boolean result = bcpe.matches(userData.getPassword(), user.getPassword());
		if (result) {
			User userExist = userRep.findByEmailAndPassword(userData.getEmail(), user.getPassword())
					.orElseThrow(() -> new ResourceNotFound("Wrong Email or Password !"));
			return ResponseEntity.ok().body(userExist);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	private byte[] UploadImage(MultipartFile file) throws java.io.IOException {
		byte[] fileContent = null;
		try {
			fileContent = file.getBytes();

		} catch (IOException e) {

		}
		return fileContent;

	}

	private void copyUserData(RegisterRequest source, User destination, MultipartFile file) throws java.io.IOException {
		destination.setUsername(source.getUname());
		destination.setPassword(source.getPwd());
		destination.setFirstName(source.getFname());
		destination.setEmail(source.getEmail());
		destination.setLastName(source.getLname());
		destination.setDate(source.getDate());
		destination.setCountry(source.getCountry());
		destination.setNumber(source.getContact());
		destination.setProfileImage(UploadImage(file));
		destination.setExp(source.getExp());
		destination.setUserRole(source.getUserRole());
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestParam(value = "type") String type,
			@RequestParam(value = "isCertificated") boolean isCertificated, @RequestPart("image") MultipartFile Image,
			@RequestPart("userData") String userJson,
			@RequestPart(value = "License", required = false) MultipartFile License) throws java.io.IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		RegisterRequest userData = objectMapper.readValue(userJson, RegisterRequest.class);
		userData.setUserRole(UserRole.valueOf(type));
		if (userRep.existsByEmail(userData.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		String hashedPassword = bcpe.encode(userData.getPwd());
		userData.setPwd(hashedPassword);
		User savedUser;

		if ("Footballer".equals(type)) {
			Footballer footballer = new Footballer();
			copyUserData(userData, footballer, Image);
			if (isCertificated) {
				footballer.setLicense(UploadImage(License));
			} else {
				footballer.setLicense(null);
			}
			footballer.setPoids(userData.getPoids());
			footballer.setTaille(userData.getTaille());
			footballer.setPos1(userData.getPos1());
			footballer.setPos2(userData.getPos2());
			footballer.setFoot(userData.getFoot());
			savedUser = footRep.save(footballer);
		} else if ("Agent".equals(type)) {
			Agent agent = new Agent();
			copyUserData(userData, agent, Image);
			agent.setDeals(userData.getDeals());
			agent.setProfile(userData.getProfile());
			savedUser = agRep.save(agent);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@GetMapping("/allAgent")
	public ResponseEntity<List<Agent>> getAllAgent() {
		return ResponseEntity.status(HttpStatus.FOUND).body(agRep.findAll());

	}
	@GetMapping("/allPost")
	public ResponseEntity<List<Poste>> getAllPost() {
		return ResponseEntity.status(HttpStatus.FOUND).body(postRep.findAll());
	}
	@GetMapping("/allTuto")
	public ResponseEntity<List<Tuto>> getAllTuto() {
		return ResponseEntity.status(HttpStatus.FOUND).body(tutoRep.findAll());
	}
	@GetMapping("/allCamps")
	public ResponseEntity<List<Camps>> getAllCamps() {
		return ResponseEntity.status(HttpStatus.FOUND).body(campsRep.findAll());
	}
	@GetMapping("/allComment/{idposte}")
	public ResponseEntity<List<Comment>> getAllComment(@PathVariable long idposte) {
		return ResponseEntity.status(HttpStatus.FOUND).body(commentRep.findCommentByPosteId(idposte));
	}
	@PostMapping("/AddComment")
	public ResponseEntity<Comment> AddComment(@RequestParam("idpost") long idpost,
			@RequestPart(value="user") String userJson,
			@RequestPart(value="date") String date,
			@RequestPart(value="type") String Type,
			@RequestPart("text") String text) throws java.io.IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			User userData=null;
			Poste p = postRep.findById(idpost).get();
		if(Type.equals("Footballer")) {
			Footballer footballerData = objectMapper.readValue(userJson, Footballer.class);
            userData = footballerData;
		}else if(Type.equals("Agent")) {
			 Agent agentData = objectMapper.readValue(userJson, Agent.class);
	            userData = agentData;
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok().body(commentRep.save(new Comment(p,text,userData,date)));
	}
	@PostMapping("/UpdateComment")
	public ResponseEntity<Comment> UpdateComment(@RequestParam("idcomment") long idcomment,
			@RequestParam("text") String text) throws java.io.IOException {
			Comment c=commentRep.findById(idcomment).get();
			c.setText(text);
		return ResponseEntity.ok().body(commentRep.save(c));
	}
	@DeleteMapping("/DeleteComment")
	public void DeleteComment(@RequestParam("idcomment") long idComment) throws java.io.IOException {
		 commentRep.deleteById(idComment);
	}
	@PostMapping("/Reagir")
	public ResponseEntity<Reagir> Reagir(@RequestParam("idpost") long idpost,
			@RequestPart(value="user") String userJson,
			@RequestPart(value="type") String Type) throws java.io.IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			User userData=null;
			Poste p = postRep.findById(idpost).get();
		if(Type.equals("Footballer")) {
			Footballer footballerData = objectMapper.readValue(userJson, Footballer.class);
            userData = footballerData;
		}else if(Type.equals("Agent")) {
			 Agent agentData = objectMapper.readValue(userJson, Agent.class);
	            userData = agentData;
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Reagir result=reagirRep.findReagirByUserIdAndPosteId(userData.getId(),idpost);
		if(result!=null) {
			reagirRep.deleteById(result.getId());
			return ResponseEntity.ok().build();
		}else {
			reagirRep.save(new Reagir(userData,p));
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		
	}
	@GetMapping("/getReagir/{iduser}/{idposte}")
	public ResponseEntity<Reagir> getReagirByUserAndposte(@PathVariable("iduser") long iduser,@PathVariable("idposte") long idposte) {
		Reagir result=reagirRep.findReagirByUserIdAndPosteId(iduser,idposte);
		if(result!=null) {
			return  ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	@GetMapping("/getAllReagir/{idposte}")
	public ResponseEntity<List<Reagir>> getAllReagir(@PathVariable("idposte") long idposte) {
		return ResponseEntity.status(HttpStatus.FOUND).body(reagirRep.findReagirByPosteId(idposte));
	}
	@GetMapping("/allFootballer")
	public ResponseEntity<List<Footballer>> getAllFootballer() {
		return ResponseEntity.status(HttpStatus.FOUND).body(footRep.findAll());
	}

	@PostMapping("/UpdateUser")
	public ResponseEntity<User> updateUser(
	        @RequestParam(value = "type") String type,
	        @RequestParam(value = "isCertificated") boolean isCertificated,
	        @RequestParam(value = "imageChanged") boolean imageChanged,
	        @RequestPart(value = "image", required = false) MultipartFile Image,
	        @RequestPart("userData") String userJson,
	        @RequestPart(value = "License", required = false) MultipartFile License)
	        throws java.io.IOException {

	    ObjectMapper objectMapper = new ObjectMapper();
	    User userData;
	        if ("Footballer".equals(type)) {
	            Footballer footballerData = objectMapper.readValue(userJson, Footballer.class);
	            userData = footballerData;
	            if (isCertificated) {
	                footballerData.setLicense(UploadImage(License));
	            }
	            if (imageChanged) {
	                footballerData.setProfileImage(UploadImage(Image));
	            }
	        } else if ("Agent".equals(type)) {
	            Agent agentData = objectMapper.readValue(userJson, Agent.class);
	            userData = agentData;
	            if (imageChanged) {
	                agentData.setProfileImage(UploadImage(Image));
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }

	        return ResponseEntity.status(HttpStatus.FOUND).body(userRep.save(userData));
	}

	public static String generateRandomPassword(int len) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}
	@PostMapping("/AddImage")
	public ResponseEntity<Image> AddImage(
			@RequestPart(value="user") String userJson,
			@RequestPart(value="image") MultipartFile image,
			@RequestPart(value="type") String Type) throws java.io.IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			User userData=null;
		if(Type.equals("Footballer")) {
			Footballer footballerData = objectMapper.readValue(userJson, Footballer.class);
            userData = footballerData;
		}else if(Type.equals("Agent")) {
			 Agent agentData = objectMapper.readValue(userJson, Agent.class);
	            userData = agentData;
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok().body(imageRep.save(new Image(UploadImage(image),userData)));
	}
	@GetMapping("/GetImageByUserId")
	public ResponseEntity<List<Image>> GetImageByUserId(
			@RequestParam(value="userid") Long userid) throws java.io.IOException {
		return ResponseEntity.ok().body(imageRep.findImageByUserId(userid));
	}
	@PostMapping("/AddVideo")
	public ResponseEntity<Video> AddVideo(
			@RequestPart(value="user") String userJson,
			@RequestPart(value="link") String link,
			@RequestPart(value="type") String Type,
			@RequestPart(value="description") String desc) throws java.io.IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			User userData=null;
		if(Type.equals("Footballer")) {
			Footballer footballerData = objectMapper.readValue(userJson, Footballer.class);
            userData = footballerData;
		}else if(Type.equals("Agent")) {
			 Agent agentData = objectMapper.readValue(userJson, Agent.class);
	            userData = agentData;
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok().body(videoRep.save(new Video(link,userData,desc)));
	}
	@GetMapping("/GetVideoByUserId")
	public ResponseEntity<List<Video>> GetVideoByUserId(
			@RequestParam(value="userid") Long userid) throws java.io.IOException {
		return ResponseEntity.ok().body(videoRep.findVideoByUserId(userid));
	}
}
