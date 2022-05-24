package com.smartf.comu.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "receive_goods")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveGoods {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "receive_date")
    private Date receiveDate;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "liter")
    private Double liter;

    @Column(name = "tank_id")
    private Long tankId;
}
