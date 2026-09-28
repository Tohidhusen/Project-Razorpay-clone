package com.project.RazorpayClone.merchant.Entity;

import com.project.RazorpayClone.common.BaseEntity;
import com.project.RazorpayClone.common.enums.BusinessType;
import com.project.RazorpayClone.common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "merchant",indexes = {
        @Index(name="idx_merchant_statues",columnList = "status")
})
public class Merchant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 200)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

     @Column(length = 20)
    private String contactNumber;
 //Enums by default give indexed value so convert it into string value
     @Column(length = 50)
     @Enumerated(EnumType.STRING)
    private BusinessType businessType;

     @Column(length = 100)
    private String businessName;

     @Column(length = 200)
    private String websiteUrl;

     @Column(nullable = false)
     @Enumerated(EnumType.STRING)
    private MerchantStatus status=MerchantStatus.PENDING_KYC;

     @Column(length = 20)
    private String gstId;

    @Column(length = 20)
    private String panId;

    @Column(length = 200)
    private String settlementBankAccount;

    @Column(length = 20)
    private String settlementBankIfsc;

    @Column(length = 200)
    private String settlementBankAccountHolderName;


}
