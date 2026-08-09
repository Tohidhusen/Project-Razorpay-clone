package com.project.RazorpayClone.operations;


import com.project.RazorpayClone.common.exception.enums.Money;
import com.project.RazorpayClone.common.exception.enums.SettlementStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Settlement {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "amountUnit", column = @Column(name = "gross_amount", nullable = false)),
                    @AttributeOverride(name = "amountValue", column = @Column(name = "gross_amount_value", nullable = false))
            }
    )
    private Money grossAmount;


    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "amountUnit", column = @Column(name = "refund_amount", nullable = false)),
                    @AttributeOverride(name = "amountValue", column = @Column(name = "refund_amount_value", nullable = false))
            }
    )
    private Money refundAmount;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "amountUnit", column = @Column(name = "fee_amount", nullable = false)),
                    @AttributeOverride(name = "amountValue", column = @Column(name = "fee_amount_value", nullable = false))
            }
    )
    private Money feeAmount;


    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "amountUnit", column = @Column(name = "gst_amount", nullable = false)),
                    @AttributeOverride(name = "amountValue", column = @Column(name = "gst_amount_value", nullable = false))
            }
    )
    private Money gstAmount;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "amountUnit", column = @Column(name = "net_amount", nullable = false)),
                    @AttributeOverride(name = "amountValue", column = @Column(name = "net_amount_value", nullable = false))
            }
    )
    private Money netAmount;

    @Enumerated(EnumType.STRING)
    private SettlementStatus status;

    @Column(nullable = false,length = 50)
    private String bankReference;

    private LocalDateTime processedAt;




}
