package com.it.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.Entity.InvoiceEntity;
import com.it.Entity.InvoicedetailEntity;
import com.it.Entity.RentEntity;
import com.it.Repository.InvoiceRepository;
import com.it.Repository.InvoicedetailRepository;
import com.it.Repository.RentRepository;
import com.it.model.InvoiceResponse;
import com.it.model.InvoicedetailResponse;
import com.it.model.RentResponse;


@RestController
public class InvoicedetailController {

	@Autowired
	private InvoicedetailRepository invoicedetailRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	private InvoicedetailResponse convertToResponse(InvoicedetailEntity entity) {
		InvoicedetailResponse response = modelMapper.map(entity, InvoicedetailResponse.class);
		
		//set invoice
		Optional<InvoiceEntity> invoiceEntity = invoiceRepository.findById(entity.getInvoice_id());
		if (invoiceEntity.isPresent()) {
			InvoiceResponse invoiceResponse = modelMapper.map(invoiceEntity.get(), InvoiceResponse.class);
		}
		return response;
  }
	
	@GetMapping("/invoicedetail")
	public ResponseEntity<List<InvoicedetailResponse>> getAllInvoicedetail(){
		List<InvoicedetailEntity> entities = invoicedetailRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/invoicedetail/{deId}")
	public ResponseEntity<InvoicedetailEntity> getInvoicedetailBydeId(@PathVariable("deId") Integer deId){
		Optional<InvoicedetailEntity> entity = invoicedetailRepository.findById(deId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/invoicedetail/save")
	public ResponseEntity<InvoicedetailEntity> saveInvoicedetail(@RequestBody InvoicedetailEntity request){
		if (request != null) {
			InvoicedetailEntity entity = new InvoicedetailEntity();
			entity.setDe_startdate(request.getDe_startdate());
			entity.setDe_enddate(request.getDe_enddate());
			entity.setDe_wa_new(request.getDe_wa_new());
			entity.setDe_li_new(request.getDe_li_new());
			entity.setDe_totalunit_wa(request.getDe_totalunit_wa());
			entity.setDe_totalunit_li(request.getDe_totalunit_li());
			entity.setDe_total_wa(request.getDe_total_wa());
			entity.setDe_total_li(request.getDe_total_li());
			entity.setDe_total(request.getDe_total());
			entity.setDe_li(request.getDe_li());
			entity.setDe_wa(request.getDe_wa());
			entity.setInvoice_id(request.getInvoice_id());
		return ResponseEntity.ok(invoicedetailRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
		}
	
	@PostMapping("/invoicedetail/update")
	public ResponseEntity<InvoicedetailEntity> updateInvoicedetail(@RequestBody InvoicedetailEntity request){
		if (request.getDe_id() != null) {
			Optional<InvoicedetailEntity> entity = invoicedetailRepository.findById(request.getDe_id());
			if (entity.isPresent()) {
				InvoicedetailEntity updateEntity = entity.get();
				updateEntity.setDe_startdate(request.getDe_startdate());
				updateEntity.setDe_enddate(request.getDe_enddate());
				updateEntity.setDe_wa_new(request.getDe_wa_new());
				updateEntity.setDe_li_new(request.getDe_li_new());
				updateEntity.setDe_totalunit_wa(request.getDe_totalunit_wa());
				updateEntity.setDe_totalunit_li(request.getDe_totalunit_li());
				updateEntity.setDe_total_wa(request.getDe_total_wa());
				updateEntity.setDe_total_li(request.getDe_total_li());
				updateEntity.setDe_total(request.getDe_total());
				updateEntity.setInvoice_id(request.getInvoice_id());
				return ResponseEntity.ok(invoicedetailRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	@GetMapping("/invoice/by-inId{inId}")
	public ResponseEntity<List<InvoicedetailResponse>> invoiceByuserId(@PathVariable("inId") Integer inId) {
//		Optional<RentEntity> entity = rentRepository.findByUserId(userId);
//		if (null != entity && entity.size() > 0) {
		//Optional<InvoicedetailEntity> findByInId(Integer inId);
		Optional<InvoicedetailEntity> entity = invoicedetailRepository.findByInId(inId);
		if (null != entity) {
			return ResponseEntity.ok(entity.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	@DeleteMapping("/invoicedetail/{deId}")
	public ResponseEntity<String> deleteinvoicedetailByDeId(@PathVariable("deId") Integer deId) {
		invoicedetailRepository.deleteById(Integer.valueOf(deId));
		return ResponseEntity.ok("SUCCESS");
	}

	
}//
