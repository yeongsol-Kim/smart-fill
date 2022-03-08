package com.smartf.comu.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthority {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "authority_name")
    private String authorityName;
}
