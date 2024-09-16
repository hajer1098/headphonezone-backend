package com.hajer.Headphone.Models;

import com.hajer.Headphone.Enum.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Cart extends EntityAbstract {


	private String orderDescription;
	private Date date;
	private Long amount;
	private String address;
	private OrderStatus orderStatus;
	private Long TotalAmount;
	private Long discount;
	private UUID trackkingId;

	@OneToOne(cascade= CascadeType.MERGE)
	@JoinColumn(name="user_id",referencedColumnName="id")
	private User user; 

	@OneToMany(fetch= FetchType.LAZY,mappedBy="order")
	private List<CartItems> cartItems;


}
