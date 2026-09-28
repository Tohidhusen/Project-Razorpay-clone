package com.project.RazorpayClone.operations;

import com.project.RazorpayClone.common.BaseEntity;
import com.project.RazorpayClone.common.enums.WebHookEventStatus;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "web_hook_events")
public class WebHookEvent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Column(nullable = false,length = 100)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> payload;

    @Column(nullable = false)
    private String targetUrl;

    @Column(nullable = false)
    private String signature;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private WebHookEventStatus status;


    @Column(nullable = false)
    private Integer attempts=0;

    private LocalDateTime nextretryAt;

    private LocalDateTime lastAttemptat;

    @Column(nullable = false)
    private Integer lastResponseCode;

    @Column(nullable = false,length = 1000)
    private String lastResponseBody;

    private LocalDateTime deliveredAt;





}
