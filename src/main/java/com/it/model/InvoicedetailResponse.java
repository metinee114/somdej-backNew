package com.it.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoicedetailResponse  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer de_id;
	private String de_startdate;
	private String de_enddate;
	private Integer de_wa_new;
	private Integer de_li_new;
	private Integer de_wa;
	private Integer de_li;
	private Integer de_totalunit_li;
	private Integer de_totalunit_wa;
	private String de_total_li;
	private String de_total_wa;
	private String de_total;
	private Integer Invoice_id;
	private InvoiceResponse invoice;
}
