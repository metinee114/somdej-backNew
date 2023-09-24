package com.it.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.service.ReportService;
import com.it.service.ReportServiceImpl;
import com.it.utils.ReportUtils;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	private static final Logger log = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReportServiceImpl reportServiceImpl;
	
	@GetMapping(path = "/generateReport")
	public ResponseEntity<InputStreamResource> generateReport() throws IOException{
		log.info("generateReport : Start");
		ResponseEntity<InputStreamResource> response = null;
		try (ByteArrayOutputStream out = reportService.generateReport()){
			if(out != null) {
				response = new  ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())),
						ReportUtils.createResponseHeader(MediaType.APPLICATION_PDF, "test.pdf", null),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("generateReport Error : {}" , e);
		}
		log.info("generateReport : End");
		return response;
	}
	
	@GetMapping(path = "/generateBilldrugReport")
	public ResponseEntity<InputStreamResource> generateBilldrugReport(@RequestParam(name = "rentId", required = true) Integer rentId) throws IOException{
		log.info("generateReport : Start :: rentId : " + rentId);
		ResponseEntity<InputStreamResource> response = null;
		try (ByteArrayOutputStream out = reportService.generateBilldrugReport(rentId)){
			if(out != null) {
				response = new  ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())),
						ReportUtils.createResponseHeader(MediaType.APPLICATION_PDF, "Report.pdf", null),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("generateReport Error : {}" , e);
		}
		log.info("generateReport : End");
		return response;
	}
	
//	@GetMapping(path = "/generateBillPayment")
//	public ResponseEntity<InputStreamResource> generateBillPayment(@RequestParam(name = "inId", required = true) Integer inId) throws IOException{
//		log.info("generateBillPayment : Start :: rentId : " + inId);
//		ResponseEntity<InputStreamResource> response = null;
//		try (ByteArrayOutputStream out = reportService.generateBillPaymentReport(inId)){
//			if(out != null) {
//				response = new  ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())),
//						ReportUtils.createResponseHeader(MediaType.APPLICATION_PDF, "Payment.pdf", null),
//						HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			log.error("generateBillPayment Error : {}" , e);
//		}
//		log.info("generateBillPayment : End");
//		return response;
//	}

//	@GetMapping(path = "/generateBilldPaymentReport")
//	public ResponseEntity<InputStreamResource> generateBilldPaymentReport(@RequestParam(name = "InId", required = true) Integer InId) throws IOException{
//		log.info("generateReport : Start :: inId : " + InId);
//		ResponseEntity<InputStreamResource> response = null;
//		try (ByteArrayOutputStream out = reportService.generateBillPaymentReport(InId)){
//			if(out != null) {
//				response = new  ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())),
//						ReportUtils.createResponseHeader(MediaType.APPLICATION_PDF, "Bill.pdf", null),
//						HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			log.error("generateReport Error : {}" , e);
//		}
//		log.info("generateReport : End");
//		return response;
//	}


	@GetMapping(path = "/generateBillOrderReport")
	public ResponseEntity<InputStreamResource> generateBillOrderReport(
			@RequestParam(name = "dateFrom")String dateFrom,
			@RequestParam(name = "dateTo")String dateTo) throws IOException{
		log.info("generateBillOrderReport : Start :: dateFrom : {}, dateTo : {}", dateFrom, dateTo);
		ResponseEntity<InputStreamResource> response = null;
		try (ByteArrayOutputStream out = reportServiceImpl.generateBillOrderReport(dateFrom, dateTo)){
			if(out != null) {
				response = new  ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())),
						ReportUtils.createResponseHeader(MediaType.APPLICATION_PDF, "test.pdf", null),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("generateBillOrderReport Error : {}" , e);
		}
		log.info("generateBillOrderReport : End");
		return response;
	}
}
