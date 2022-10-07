package com.smartf.comu.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tank_fill")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TankFill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "amount")
    private Integer amount;

}
