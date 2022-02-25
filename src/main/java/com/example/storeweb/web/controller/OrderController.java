package com.example.storeweb.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storeweb.entity.Book;
import com.example.storeweb.entity.OrderItem;
import com.example.storeweb.service.BookService;
import com.example.storeweb.service.OrderService;
import com.example.storeweb.web.form.OrderForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

	private final BookService bookService;
	private final OrderService orderService;
	
	@GetMapping("/form")
	public String orderform(@RequestParam("id") int bookId, @RequestParam("quantity") int quantity, Model model) {
		log.info("바로 구매하기");
		log.info("주문할 book_id [" + bookId + "]");
		log.info("주문할 quantity [" + quantity + "]");
		
		Book book = bookService.getBookDetail(bookId);
		
		int totalPrice = book.getPrice()*quantity;
		int totalPaymentPrice = book.getDiscountPrice()*quantity;
		
		model.addAttribute("totalPrice", book.getPrice());
		model.addAttribute("totalDiscountPrice", totalPrice - totalPaymentPrice);
		model.addAttribute("totalPaymentPrice", totalPaymentPrice);
		
		OrderItem orderItem = new OrderItem(book, book.getDiscountPrice(), quantity);
		model.addAttribute("orderItems", List.of(orderItem));		
		
		return "/order/form";
	}
	
	@PostMapping("/insert")
	public String insert(OrderForm orderForm) {
		log.info("주문 정보 저장하기");
		log.info("주문정보 : " + orderForm);
		
		orderService.insertOrder(orderForm);
		
		return "redirect:/order/completed";
	}
	
	@GetMapping("/completed")
	public String completed() {
		
		return "order/completed";
	}
	
}
