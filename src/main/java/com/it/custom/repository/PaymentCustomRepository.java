package com.it.custom.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.it.Entity.PaymentEntity;

@Repository
public class PaymentCustomRepository {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PaymentEntity> searchPaymentByCriteria(String payDateOne,String payDateTwo) {
		List<PaymentEntity> response = new ArrayList<>();
		StringBuilder sqlBuilder = new StringBuilder();

		sqlBuilder.append("SELECT pm.* FROM tb_payment pm ");
//		sqlBuilder.append(" INNER JOIN tb_user user ON user.user_id = tm.user_id ");
//		WHERE pm.pay_date BETWEEN "2023-09-08"  AND  "2023-09-10"
		sqlBuilder.append("WHERE ");

		if (StringUtils.isNotBlank(payDateOne)) {
			sqlBuilder.append("pm.pay_date BETWEEN :payDateOne ");
		}

		if (StringUtils.isNotBlank(payDateTwo)) {
			sqlBuilder.append("AND :payDateTwo ");

			System.out.println("SQL :: " + sqlBuilder.toString());
			Query query = em.createNativeQuery(sqlBuilder.toString(), PaymentEntity.class);

			if (StringUtils.isNotBlank(payDateOne)) {
				query.setParameter("payDateOne", payDateOne);
			}
			
			if (StringUtils.isNotBlank(payDateTwo)) {
				query.setParameter("payDateTwo", payDateTwo);
			}

			response = query.getResultList();

			return response;
		}
		return response;
	}
}// end
