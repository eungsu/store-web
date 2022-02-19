package com.example.storeweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_author", nullable = false)
    private String author;

    @Column(name = "book_publisher", nullable = false)
    private String publisher;

    @Column(name = "book_description")
    private String description;

    @Column(name = "book_price")
    private int price;

    @Column(name = "book_discount_price")
    private int discountPrice;

    @Column(name = "book_on_sale")
    private boolean onSale;

    @Column(name = "book_stock")
    private int stock;
}
