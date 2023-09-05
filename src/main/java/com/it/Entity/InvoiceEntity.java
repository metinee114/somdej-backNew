package com.it.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="tb_invoice")
	
public class InvoiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  Invoice_id;
	private String Invoice_stetus;
	private String Invoice_end;
	private String Invoice_start;
	private Integer Invoice_total;
	private Integer roomId;
	private Integer userId;
	private Integer rentId;
	//private Integer inId;
	
	public Integer getInvoice_id() {
		return Invoice_id;
	}
	public void setInvoice_id(Integer invoice_id) {
		Invoice_id = invoice_id;
	}
	public String getInvoice_stetus() {
		return Invoice_stetus;
	}
	public void setInvoice_stetus(String invoice_stetus) {
		Invoice_stetus = invoice_stetus;
	}
	public String getInvoice_end() {
		return Invoice_end;
	}
	public void setInvoice_end(String invoice_end) {
		Invoice_end = invoice_end;
	}
	public String getInvoice_start() {
		return Invoice_start;
	}
	public void setInvoice_start(String invoice_start) {
		Invoice_start = invoice_start;
	}
	public Integer getInvoice_total() {
		return Invoice_total;
	}
	public void setInvoice_total(Integer invoice_total) {
		Invoice_total = invoice_total;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	
	
	

}
