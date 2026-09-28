package com.project.RazorpayClone.merchant.Entity;

import com.project.RazorpayClone.common.BaseEntity;
import com.project.RazorpayClone.common.enums.Environment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "api_key", indexes = {
        @Index(name = "idx_api_key_merchant_env", columnList = "merchant_id,environment,enabled")
}
)
public class ApiKey extends BaseEntity {


    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false,length = 50,unique = true)
    private String keyId;

    @Column(length = 200,nullable = false)
    private String keySecretHash;


    @Column(length = 200)
    private String previouskeySecretHash;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 10)
    private Environment environment;

    @Column(nullable = false)
    @Builder.Default
    private Boolean enabled=true;


    private LocalDateTime lastUsedAt;
    private LocalDateTime rotatedAt;
    private LocalDateTime gracePeriodExpireAt;
}
