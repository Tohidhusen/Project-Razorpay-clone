package com.project.RazorpayClone.merchant.entity;

import com.project.RazorpayClone.common.exception.enums.BusinessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "merchant")
public class Merchant {

    private UUID id;
    private String name;
    private String email;
    private String contactNumber;
    private BusinessType businesstype;
    private String businessName;
    private String websiteUrl;

}
