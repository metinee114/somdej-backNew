package com.it.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer rentId;
	private String rentStart;
	private String rentEnd;
	private Integer rentInsurance;
	private String rentTotalprice;
	private String cardTime;
	private String cardAddress;
	private String rentOther;
	private String rentWa;
	private String rentLi;
	private Integer userId;
	private String roomId;
	private String stetus;
	private String fileName;
	private UserResponse user;
	private RoomResponse room;
	
	
	
}
