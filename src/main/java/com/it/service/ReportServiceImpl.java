package com.it.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.it.dto.PaymentReportDto;
import com.it.model.PaymentResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	private PaymentReport paymentReport ;

	public ByteArrayOutputStream generateBillOrderReport(String dateFromStr, String dateToStr) throws IOException {
		log.info("generateBilOrderReport : Start :: dateFrom : {}, dateTo : {}", dateFromStr, dateToStr);
		ByteArrayOutputStream out = null;
		try {
			List<PaymentResponse> billorderDtos = paymentReport.findBillorderForReport(dateFromStr, dateToStr);
			if (CollectionUtils.isNotEmpty(billorderDtos)) {
				List<PaymentReportDto> billOrderReportDtos = convertToBillOrderReportDto(billorderDtos);
				ClassPathResource reportFile = new ClassPathResource("report/PaymentReport.jasper");
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());

				Map<String, Object> parameters = new HashMap<>();
				// parameters.put("ctmId", String.valueOf(ctmId));

				JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(billOrderReportDtos);

				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
				out = new ByteArrayOutputStream();

				JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			}

		} catch (Exception e) {
			log.error("generateTreatmentReport Error : {} ", e);
		}
		log.info("generateTreatmentReport : End");
		return out;
	}

	private List<PaymentReportDto> convertToBillOrderReportDto(List<PaymentResponse> billorderDtos) {
		List<PaymentReportDto> result = new ArrayList<PaymentReportDto>();
		if (CollectionUtils.isNotEmpty(billorderDtos)) {
			for (PaymentResponse dto : billorderDtos) {
//				String payDate = new SimpleDateFormat("yyyy-MM-dd").format(dto.getPayDate());
				PaymentReportDto payment = PaymentReportDto.builder()
						.payId(dto.getPayId())
						.payDate(dto.getPayDate())
						.payTotal(new BigDecimal(dto.getPayTotal()))
						.invoiceStart(dto.getInvoice().getInvoice_start())
						.userName(dto.getInvoice().getRent().getUser().getUserName())
						.userLasname(dto.getInvoice().getRent().getUser().getUserLasname())
						.userTitle(dto.getInvoice().getRent().getUser().getUserTitle())
						.userPhone(dto.getInvoice().getRent().getUser().getUserPhone())
						.roomName(dto.getInvoice().getRent().getRoom().getRoomname())
						.build();
//				List<PaymentReportDto> billOrderReportDtos = dto.getOrderdetails().stream().map(d -> {
//					String payDate = new SimpleDateFormat("dd/MM/yyyy").format(dto.getPayDate());
//					return PaymentReportDto.builder().payDate(payDate)
//							.payId(dto.getPayId())
//							.payDate(dto.getPayDate())
//							.payTotal(dto.getPayTotal())
//							.userName(dto.getInvoice().getRent().getUser().getUserName())
//							.userLasname(dto.getInvoice().getRent().getUser().getUserLasname())
//							.userTitle(dto.getInvoice().getRent().getUser().getUserTitle())
//							.userPhone(dto.getInvoice().getRent().getUser().getUserPhone())
//							.roomName(dto.getInvoice().getRent().getRoom().getRoomname())
//							.build();
//							
//					        
//				}).collect(Collectors.toList());
				
				result.add(payment);
			}
		}
		return result;
	}

}
