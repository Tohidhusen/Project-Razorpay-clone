package com.project.RazorpayClone.payment.Entity;

import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class OrderRecord {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    //no Fk
    @Column(name = "merchant_id",nullable = false)
    private UUID merchantId;

    @Embedded
    //two more columns are coming from money table inside this entity
    private Money amount;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.CREATED;

    @Column(nullable = false)
    private Integer attempts=0;

    @Column(columnDefinition = "jsonb", length = 1000)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> notes;

    @Column(nullable = false)
    private LocalDateTime expireAt;
}
