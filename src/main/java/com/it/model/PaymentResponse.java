package com.it.model;

import java.io.Serializable;
//import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer  payId;
	private String payDate;
	private String payTotal;
	private Integer InId;
	private InvoiceResponse invoice;
	
}
