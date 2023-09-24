package com.it.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderdetailsDto {
	private Integer detailsId;
	private Integer quantity;
	private String unit;
	private BigDecimal totalAmout;
	private BigDecimal unitPrice;
	private BigDecimal totalOrder;
	private BigDecimal totalBalance;
	private String status;
	private BigDecimal detailsFreight;
	private Integer billId;
	private Integer proId;
	private Integer ctmId;
	private String parcelNumber;
}
