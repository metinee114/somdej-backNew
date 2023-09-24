package com.it.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.Entity.InvoiceEntity;
import com.it.Entity.InvoicedetailEntity;

public interface InvoicedetailRepository extends JpaRepository<InvoicedetailEntity, Integer>{
	
	@Query(value = "SELECT * FROM tb_invoicedetail WHERE Invoice_id =?1 " , nativeQuery = true)
	public Optional<InvoicedetailEntity> findByInId(Integer invoice_id);
	
	//public List<InvoicedetailEntity> findByinvoiceId(Integer invoice_id);
	
	@Query("select t from InvoicedetailEntity t where DATE(t.de_startdate) >= ?1 AND DATE(t.de_enddate) <=?2 ")
    public List<InvoicedetailEntity> findBySerach(Date startDate, Date endDate);
	

}
