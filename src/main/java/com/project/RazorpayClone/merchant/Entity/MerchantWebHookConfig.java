package com.project.RazorpayClone.merchant.Entity;

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
@Table(name = "merchant_webhook_config")
public class MerchantWebHookConfig {

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
