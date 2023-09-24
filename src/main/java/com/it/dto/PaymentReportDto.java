package com.it.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.it.model.InvoiceResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentReportDto {

	private Integer  payId;
	private String payDate;
	private BigDecimal payTotal;
	private Integer Invoice_id;
	private String invoiceStart;
	private String userTitle;
	private String userName;
	private String userLasname;
	private String userPhone;
	private String roomName;
	
	private InvoiceResponse invoice;
}
