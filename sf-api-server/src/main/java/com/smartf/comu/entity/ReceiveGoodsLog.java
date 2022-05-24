package com.smartf.comu.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "receive_goods_log")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveGoodsLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "tank_id")
    private Long tankId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "receive_goods_datetime")
    private LocalDateTime receiveGoodsDatetime;
}
