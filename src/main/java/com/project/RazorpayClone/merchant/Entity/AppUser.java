package com.project.RazorpayClone.merchant.Entity;

import com.project.RazorpayClone.common.BaseEntity;
import com.project.RazorpayClone.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name ="app_user" ,indexes = {
        @Index(name="idx_app_user_merchant_id",columnList = "merchant_id")
})
public class AppUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id",nullable = false)
    private Merchant merchant;

    @Column(nullable = false,unique = true,length = 20)
    private String email;
    @Column(nullable = false,unique = true)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

}
