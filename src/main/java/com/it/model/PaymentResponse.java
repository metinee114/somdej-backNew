package com.it.model;

import java.io.Serializable;
//import java.util.Date;
import java.util.Collection;
import java.util.List;

import com.it.dto.OrderdetailsDto;

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
	private Integer Invoice_id;
	private InvoiceResponse invoice;
	
}
