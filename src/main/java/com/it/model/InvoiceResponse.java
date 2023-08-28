package com.it.model;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer  Invoice_id;
	private String Invoice_stetus;
	private String Invoice_end;
	private String Invoice_start;
	private Integer Invoice_total;
	private Integer rentId;
	private String roomId;
	private String userId;
	private RentResponse rent;
	private UserResponse user;
	private RoomResponse room;

}
