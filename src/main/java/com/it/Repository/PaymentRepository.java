package com.it.Repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import com.it.Entity.InvoiceEntity;
import com.it.Entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{

	@Query(value = "SELECT * FROM tb_payment WHERE Invoice_id =?1" , nativeQuery = true)
	public List<PaymentEntity> findByInId(Integer Invoice_id);
	
	@Query(value = "SELECT b FROM PaymentEntity b WHERE date(b.payDate) BETWEEN STR_TO_DATE(:dateFromStr, '%Y-%m-%d') AND STR_TO_DATE(:dateToStr, '%Y-%m-%d')")
	List<PaymentEntity> findBillorderForReport(@Param("dateFromStr")String dateFromStr, @Param("dateToStr")String dateToStr);
}
