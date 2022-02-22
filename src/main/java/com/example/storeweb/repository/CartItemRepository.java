package com.example.storeweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storeweb.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
	Optional<CartItem> findByMemberIdAndBookId(Long memberId, Long bookId);
	List<CartItem> findAllByMemberId(Long memberId);
}
