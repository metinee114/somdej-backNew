package com.it.Entity;



//import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_payment")
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  payId;

	private String payDate;
	private Integer Invoice_id;
	private String fileName;
	private String payTotal;
//	private Integer user_id;
//	private Integer room_id;
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public Integer getInvoice_id() {
		return Invoice_id;
	}
	public void setInvoice_id(Integer invoice_id) {
		Invoice_id = invoice_id;
	}


	
	//private Integer InId;
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
	

	public String getPayTotal() {
		return payTotal;
	}
	public void setPayTotal(String payTotal) {
		this.payTotal = payTotal;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
