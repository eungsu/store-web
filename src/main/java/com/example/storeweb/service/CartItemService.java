package com.example.storeweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.storeweb.entity.Book;
import com.example.storeweb.entity.CartItem;
import com.example.storeweb.entity.Member;
import com.example.storeweb.repository.BookRepository;
import com.example.storeweb.repository.CartItemRepository;
import com.example.storeweb.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartItemService {

	private final BookRepository bookRepository;
	private final CartItemRepository cartItemRepository;
	private final MemberRepository memberRepository;
	
	public void insertCartItem(long memberId, long bookId, int quantity) {
		
		Optional<CartItem> optional = cartItemRepository.findByMemberIdAndBookId(memberId, bookId);
		if (optional.isEmpty()) {
			Member member = memberRepository.getById(memberId);
			Book book = bookRepository.getById(bookId);
			
			CartItem cartItem = new CartItem();
			cartItem.setMember(member);
			cartItem.setBook(book);
			cartItem.setQuantity(quantity);
			
			cartItemRepository.save(cartItem);
			
		} else {
			CartItem cartItem = optional.get();
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
			
			cartItemRepository.save(cartItem);
		}
		
	}

	public List<CartItem> getCartItems(long memberId) {
		return cartItemRepository.findAllByMemberId(memberId);
	}

	public void updateCartItem(long memberId, long cartItemId, int quantity) {
		CartItem cartItem = cartItemRepository.getById(cartItemId);
		
		if (cartItem.getMember().getId() == memberId) {
			cartItem.setQuantity(quantity);
			cartItemRepository.save(cartItem);
		}		
	}

	public void deleteCartItem(long memberId, List<Long> cartItemIds) {
		for (long cartItemId : cartItemIds) {
			CartItem cartItem = cartItemRepository.getById(cartItemId);
			
			if (cartItem.getMember().getId() == memberId) {
				cartItemRepository.delete(cartItem);
			}	
		}
		
	}
	
}
