package com.smartf.comu.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fill_logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datetime", length = 50)
    private String datetime;

    @Column(name = "liter", length = 50)
    private Long liter;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    @Column(name = "user_id", length = 50)
    private Long userId;
}
