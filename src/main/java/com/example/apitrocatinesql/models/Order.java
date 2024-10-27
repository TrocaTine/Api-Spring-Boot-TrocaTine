package com.example.apitrocatinesql.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @ManyToOne
    @JoinColumn(name = "id_shopping_cart_old")
    private HistoryShoppingCart shoppingCartOld;

    @ManyToOne
    @JoinColumn(name = "id_saved_card")
    private SavedCard savedCard;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "accounted_at")
    private LocalDate accountedAt;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

}

