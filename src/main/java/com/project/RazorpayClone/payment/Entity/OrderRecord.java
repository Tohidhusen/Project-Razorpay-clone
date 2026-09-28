package com.project.RazorpayClone.payment.Entity;

import com.project.RazorpayClone.common.BaseEntity;
import com.project.RazorpayClone.common.enums.Money;
import com.project.RazorpayClone.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "orders",indexes = {
        @Index(name="idx_order_merchant_id",columnList = "merchant_id"),
        @Index(name = "idx_order_merchant_id",columnList = "merchant_id")
})
@Builder
public class OrderRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    //no Fk
    @Column(name = "merchant_id",nullable = false)
    private UUID merchantId;

    @Embedded
    //two more columns are coming from money table inside this entity
    private Money amount;

     private String receipt;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status=OrderStatus.CREATED;

    @Column(nullable = false)
    @Builder.Default
    private Integer attempts=0;

    @Column(columnDefinition = "jsonb", length = 1000)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> notes;

    @Column(nullable = false)
    private LocalDateTime expireAt;
}
