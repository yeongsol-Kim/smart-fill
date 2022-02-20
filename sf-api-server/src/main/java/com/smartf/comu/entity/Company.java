package com.smartf.comu.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ceo")
    private String ceo;

    @Column(name = "tel")
    private String tel;

    @Column(name = "address")
    private String address;


}
