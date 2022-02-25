package com.example.storeweb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.storeweb.constant.OrderStatus;
import com.example.storeweb.entity.Book;
import com.example.storeweb.entity.Order;
import com.example.storeweb.entity.OrderItem;
import com.example.storeweb.repository.BookRepository;
import com.example.storeweb.repository.MemberRepository;
import com.example.storeweb.repository.OrderItemRepository;
import com.example.storeweb.repository.OrderRepository;
import com.example.storeweb.util.SecurityUtils;
import com.example.storeweb.web.form.OrderForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;
	
	public void insertOrder(OrderForm orderForm) {
		Order order = new Order();
		order.setMember(memberRepository.getById(SecurityUtils.getMemberId()));
		order.setTitle(this.getTitle(orderForm));
		order.setOrderStatus(OrderStatus.ORDER);
		order.setTotalPrice(this.getTotalPrice(orderForm));
		order.setTotalQuantity(this.getTotalQuantity(orderForm));
		order.setUsePoint(this.getUsePoint(orderForm));
		order.setDepositPoint(this.getDepositPoint(orderForm));
		order.setPaymentPrice(this.getPaymentPrice(orderForm));
		
		orderRepository.save(order);
		
		
		
	}

	private String getTitle(OrderForm orderForm) {
		long bookId = orderForm.getIds()[0];
		Book book = bookRepository.getById(bookId);
		if (orderForm.getIds().length > 1) {
			return book.getTitle() + " 외 " + (orderForm.getIds().length - 1) + "종";
		}
		return book.getTitle();
	}
	private int getTotalPrice(OrderForm orderForm) {
		return Arrays.stream(orderForm.getPrices()).sum();
	}
	private int getTotalQuantity(OrderForm orderForm) {
		return Arrays.stream(orderForm.getQuantities()).sum();
	}
	private int getUsePoint(OrderForm orderForm) {
		return orderForm.getUsePoint();
	}
	private int getDepositPoint(OrderForm orderForm) {
		return (int) (getPaymentPrice(orderForm)*0.03);
	}
	private int getPaymentPrice(OrderForm orderForm) {
		return getTotalPrice(orderForm) - getUsePoint(orderForm);
	}
}
