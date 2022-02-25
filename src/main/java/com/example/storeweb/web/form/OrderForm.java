package com.example.storeweb.web.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class OrderForm {

	private long[] ids;
	private int[] prices;
	private int[] quantities;
	private int usePoint;
}

