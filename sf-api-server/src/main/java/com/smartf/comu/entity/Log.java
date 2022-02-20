package com.smartf.comu.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "fill_logs")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datetime", length = 50)
    private OffsetDateTime dateTime;

//    @ManyToOne
//    @JoinColumn(name = "pump_id")
    @Column(name = "pump_id", length = 50)
    private Long pumpId;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    @Column(name = "user_id", length = 50)
    private Long userId;



    //-----------

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_ceo")
    private String branchCeo;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "branch_TEL")
    private String branchTEL;

    @Column(name = "approval_number")
    private String approvalNumber;

    @Column(name = "pump_number")
    private Long pumpNumber;

    @Column(name = "product")
    private String product;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "username")
    private String username;

    @Column(name = "car_number")
    private String carNumber;


}
