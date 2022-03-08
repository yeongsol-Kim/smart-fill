package com.smartf.comu.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminAuthority {

    @Id
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "authority_name")
    private String authorityName;

}
