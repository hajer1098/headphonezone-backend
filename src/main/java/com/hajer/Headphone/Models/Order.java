package com.hajer.Headphone.Models;


import com.hajer.Headphone.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ordre")
public class Order  extends EntityAbstract{


	private String orderDescription;
	private Date date;
	private float amount;
	private String address;
	private OrderStatus orderStatus;
	private float TotalAmount;
	private float discount;
	private UUID trackkingId;

	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id",referencedColumnName="id")
	private User user;
     //one order can contain many articles
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<CartItems> cartItems;

}
