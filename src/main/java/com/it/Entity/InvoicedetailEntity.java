package com.it.Entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_invoicedetail")
public class InvoicedetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer de_id;
	private String de_startdate;
	private String de_enddate;
	private String de_wa_new;
	private String de_li_new;
	private Integer de_totalunit_li;
	private Integer de_totalunit_wa;
	private String de_total_li;
	private String de_total_wa;
	private String de_total;
	private Integer Invoice_id;
	private String de_wa;
	private String de_li;
	
	public Integer getDe_id() {
		return de_id;
	}
	public void setDe_id(Integer de_id) {
		this.de_id = de_id;
	}
	public String getDe_startdate() {
		return de_startdate;
	}
	public void setDe_startdate(String de_startdate) {
		this.de_startdate = de_startdate;
	}
	public String getDe_enddate() {
		return de_enddate;
	}
	public void setDe_enddate(String de_enddate) {
		this.de_enddate = de_enddate;
	}
	public String getDe_wa_new() {
		return de_wa_new;
	}
	public void setDe_wa_new(String de_wa_new) {
		this.de_wa_new = de_wa_new;
	}
	public String getDe_li_new() {
		return de_li_new;
	}
	public void setDe_li_new(String de_li_new) {
		this.de_li_new = de_li_new;
	}
	public Integer getDe_totalunit_li() {
		return de_totalunit_li;
	}
	public void setDe_totalunit_li(Integer de_totalunit_li) {
		this.de_totalunit_li = de_totalunit_li;
	}
	public Integer getDe_totalunit_wa() {
		return de_totalunit_wa;
	}
	public void setDe_totalunit_wa(Integer de_totalunit_wa) {
		this.de_totalunit_wa = de_totalunit_wa;
	}
	public String getDe_total_li() {
		return de_total_li;
	}
	public void setDe_total_li(String de_total_li) {
		this.de_total_li = de_total_li;
	}
	public String getDe_total_wa() {
		return de_total_wa;
	}
	public void setDe_total_wa(String de_total_wa) {
		this.de_total_wa = de_total_wa;
	}
	public String getDe_total() {
		return de_total;
	}
	public void setDe_total(String de_total) {
		this.de_total = de_total;
	}
	public Integer getInvoice_id() {
		return Invoice_id;
	}
	public void setInvoice_id(Integer invoice_id) {
		Invoice_id = invoice_id;
	}
	public String getDe_wa() {
		return de_wa;
	}
	public void setDe_wa(String de_wa) {
		this.de_wa = de_wa;
	}
	public String getDe_li() {
		return de_li;
	}
	public void setDe_li(String de_li) {
		this.de_li = de_li;
	}

	

//	private String InStart;
//	private String InEnd;
//	private Integer inId;
	
}



