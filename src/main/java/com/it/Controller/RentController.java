package com.it.Controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.it.Entity.ContactEntity;
import com.it.Entity.InvoiceEntity;
import com.it.Entity.PaymentEntity;
import com.it.Entity.RentEntity;
import com.it.Entity.RoomEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.RentRepository;
import com.it.Repository.RoomRepository;
import com.it.Repository.UserRepository;
import com.it.model.RentResponse;
import com.it.model.RoomResponse;
import com.it.model.UserResponse;
import com.it.service.SendEmailService;
import com.it.utils.ReportUtils;

@RestController

public class RentController {

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${file.receipt.path}")
	private String FILE_RECEIPT_PATH;

	@Autowired
	private SendEmailService sendEmailService;

	public RentResponse convertToResponse(RentEntity entity) {
		RentResponse response = modelMapper.map(entity, RentResponse.class);

		// set user
		Optional<UserEntity> userEntity = userRepository.findById(entity.getUserId());
		if (userEntity.isPresent()) {
			response.setUser(modelMapper.map(userEntity.get(), UserResponse.class));

			// set room
			Optional<RoomEntity> roomEntity = roomRepository.findById(Integer.valueOf(entity.getRoomId()));// ถ้าเป็น
																											// autokey
																											// ให้ใส่
																											// Integer.valueOf
			if (roomEntity.isPresent()) {
				response.setRoom(modelMapper.map(roomEntity.get(), RoomResponse.class));
			}
		}
		return response;
	}

	@GetMapping("/rent")
	public ResponseEntity<List<RentResponse>> getAllRent() {
		List<RentEntity> entities = rentRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/rent/getByrentId")
	public ResponseEntity<List<RentResponse>> getRentByrentId(@RequestParam("rentId") Integer rentId) {
		Optional<RentEntity> entity = rentRepository.findById(rentId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/rent/by-userId{userId}")
	public ResponseEntity<List<RentResponse>> getRentByuserId(@PathVariable("userId") Integer userId) {
//		Optional<RentEntity> entity = rentRepository.findByUserId(userId);
//		if (null != entity && entity.size() > 0) {
		Optional<RentEntity> entity = rentRepository.findByUserId(userId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/rent/save")
	public ResponseEntity<RentEntity> saveRent(@RequestBody RentEntity request) {
		if (request != null) {
			RentEntity entity = new RentEntity();
			entity.setRentStart(request.getRentStart());
			entity.setRentEnd(request.getRentEnd());
			entity.setFileName("null");
			entity.setRentInsurance(request.getRentInsurance());
			entity.setRentTotalprice(request.getRentTotalprice());
			entity.setCardTime(request.getCardTime());
			entity.setCardAddress(request.getCardAddress());
			entity.setStetus(request.getStetus());
			entity.setRentLi(request.getRentLi());
			entity.setRentWa(request.getRentWa());
			entity.setUserId(request.getUserId());
			entity.setRoomId(request.getRoomId());
			
			
			Optional<UserEntity> user = userRepository.findById(request.getUserId());
			if (user.isPresent()) {
				user.get().setCardTime(request.getCardTime());
				user.get().setCardAddress(request.getCardAddress());
				user.get().setRoomId(request.getRoomId());
				userRepository.save(user.get());
			}
			Optional<RoomEntity> room = roomRepository.findById(request.getRoomId());
			if (room.isPresent()) {
				room.get().setRoomStatus("ไม่ว่าง");
				roomRepository.save(room.get());
			}
			entity = rentRepository.save(entity);

			// SendEmailRegister
			//sendEmailService.sendEmailRegister(entity.getRentId());
			return ResponseEntity.ok(entity);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/rent/update")
	public ResponseEntity<RentEntity> updateRent(@RequestBody RentEntity request) {
		if (request.getRentId() != null) {
			Optional<RentEntity> entity = rentRepository.findById(request.getRentId());
			if (entity.isPresent()) {
				RentEntity updateEntity = entity.get();
				updateEntity.setRentStart(request.getRentStart());
				updateEntity.setRentEnd(request.getRentEnd());
				//entity.setFileName("null");
				updateEntity.setRentInsurance(request.getRentInsurance());
				updateEntity.setRentTotalprice(request.getRentTotalprice());
				updateEntity.setCardTime(request.getCardTime());
				updateEntity.setCardAddress(request.getCardAddress());
				updateEntity.setStetus(request.getStetus());
				updateEntity.setRentLi(request.getRentLi());
				updateEntity.setRentWa(request.getRentWa());
				updateEntity.setUserId(request.getUserId());
				updateEntity.setRoomId(request.getRoomId());
				
				Optional<UserEntity> user = userRepository.findById(request.getUserId());
				if (user.isPresent()) {
					user.get().setCardTime(request.getCardTime());
					user.get().setCardAddress(request.getCardAddress());
					user.get().setRoomId(request.getRoomId());
					userRepository.save(user.get());
				}
				return ResponseEntity.ok(rentRepository.save(updateEntity));
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/rent/updateByRentId")
	public ResponseEntity<RentEntity> updateByRentId(@RequestParam("rentId") Integer rentId ,@RequestParam("stetus") String stetus  ) {
		if (rentId != null) {
			Optional<RentEntity> entity = rentRepository.findById(rentId);
			if (entity.isPresent()) {
				RentEntity updateEntity = entity.get();
				updateEntity.setStetus(stetus);
				
				//Optional<RentEntity> userIdentity = rentRepository.findByUserId(entity.get().getUserId());
				Optional<UserEntity> user = userRepository.findById(entity.get().getUserId());
				if (user.isPresent()) {
					user.get().setRoomId(0);
					userRepository.save(user.get());
				}
				
				Optional<RoomEntity> room = roomRepository.findById(entity.get().getRoomId());
				if (room.isPresent()) {
					room.get().setRoomStatus("ว่าง");
					roomRepository.save(room.get());
				}
				
				return ResponseEntity.ok(rentRepository.save(updateEntity));
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@DeleteMapping("/rent/rentId")
	public ResponseEntity<String> deleteRentByRentId(@RequestParam("rentId") Integer rentId ) {
		
		rentRepository.deleteById(rentId);
		Optional<RentEntity> entity = rentRepository.findById(rentId);
		
		Optional<RoomEntity> room = roomRepository.findById(entity.get().getRoomId());
		Optional<UserEntity> user = userRepository.findById(entity.get().getUserId());
		if (user.isPresent()) {
			user.get().setRoomId(0);
			userRepository.save(user.get());
		}
		if (room.isPresent()) {
			room.get().setRoomStatus("ว่าง");
			roomRepository.save(room.get());
		}
		return ResponseEntity.ok("SUCCESS");

	}
	
	@PostMapping("/rent/uploadFile")
	public Object uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile,
			@RequestParam("RentId") Integer RentId) {
		Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = formatter.format(new Date());
		String fileName = String.valueOf(RentId) + "_" + s + "." + multipartFile.getOriginalFilename().split("\\.")[1];
		File file = new File(FILE_RECEIPT_PATH + fileName);

		try (OutputStream os = new FileOutputStream(file)) {
			os.write(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Optional<RentEntity> entity = rentRepository.findById(RentId);
		if (null != entity) {
			RentEntity contact = entity.get();
			contact.setFileName(fileName);
			rentRepository.save(contact);
		}

		return "SUCCESS";
	}
	@GetMapping(path = "/rent/downLoadFiles")
	public ResponseEntity<InputStreamResource> downLoadFile(@RequestParam("RentId") Integer RentId) throws IOException {
		ResponseEntity<InputStreamResource> response = null;
		Optional<RentEntity> entity = rentRepository.findById(RentId);
		if (null != entity) {
			RentEntity contact = entity.get();
			byte[] array = Files.readAllBytes(Paths.get(FILE_RECEIPT_PATH + contact.getFileName()));
			response = new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(array)),
					ReportUtils.createResponseHeader(MediaType.TEXT_PLAIN, "contact.getConFilename()", null), HttpStatus.OK);
		}

		return response;
	}


}
