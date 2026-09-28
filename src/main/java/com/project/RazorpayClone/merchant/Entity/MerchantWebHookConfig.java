package com.project.RazorpayClone.merchant.Entity;

import com.project.RazorpayClone.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "merchant_webhook_config",indexes = {
        @Index(name="idx_webhook_merchant_id",columnList = "merchant_id,enabled")
})
public class MerchantWebHookConfig extends BaseEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(length = 200, nullable = false)
    private String targetUrl;//www.zara.com/webhook/sucess,failure

    @Column(length = 200, nullable = false)
    private String webHookSecretHash;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(length = 200)
    private String eventTypes;
}
