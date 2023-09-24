package com.it.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.it.Entity.InvoiceEntity;
import com.it.Entity.PaymentEntity;
import com.it.Entity.RentEntity;
import com.it.Entity.RoomEntity;
import com.it.Entity.UserEntity;
import com.it.Repository.InvoiceRepository;
import com.it.Repository.PaymentRepository;
import com.it.Repository.RentRepository;
import com.it.Repository.RoomRepository;
import com.it.Repository.UserRepository;
import com.it.custom.repository.PaymentCustomRepository;
import com.it.model.InvoiceResponse;
import com.it.model.PaymentResponse;
import com.it.model.RentResponse;
import com.it.model.RoomResponse;
import com.it.model.UserResponse;

@Service
public class PaymentReport {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RentRepository rentRepository ;
	
	@Autowired
	private PaymentCustomRepository paymentCustomRepository;
	
	
	public List<PaymentResponse> findBillorderForReport(String dateFromStr, String dateToStr) {
		List<PaymentEntity> billorderEntities = paymentRepository.findBillorderForReport(dateFromStr, dateToStr);
		if (CollectionUtils.isNotEmpty(billorderEntities)) {
			return billorderEntities.stream().map(this::convertToBillorderDto).collect(Collectors.toList());
		}
		return null;
	}
	
//	**********************************************************************************
	private PaymentResponse convertToBillorderDto(PaymentEntity entity) {
		PaymentResponse response = modelMapper.map(entity, PaymentResponse.class);


		Optional<InvoiceEntity> invoiceEntity = invoiceRepository.findById(Integer.valueOf(entity.getInvoice_id()));
		if (invoiceEntity.isPresent()) {
			response.setInvoice(modelMapper.map(invoiceEntity.get(), InvoiceResponse.class));

			// set rent
			Optional<RentEntity> rentEntity = rentRepository.findById(invoiceEntity.get().getRentId());
			if (rentEntity.isPresent()) {

				response.getInvoice().setRent(modelMapper.map(rentEntity.get(), RentResponse.class));
				RentResponse rent = response.getInvoice().getRent();
				// set user
				Optional<UserEntity> userEntity = userRepository.findById(invoiceEntity.get().getUserId());
				if (userEntity.isPresent()) {
					rent.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
				}

				// set room
				Optional<RoomEntity> roomEntity = roomRepository.findById(invoiceEntity.get().getRoomId());
				if (roomEntity.isPresent()) {
					rent.setRoom(modelMapper.map(roomEntity.get(), RoomResponse.class));
				}
			}

		}
		
		return response;
	}
	
}//end

