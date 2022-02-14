package com.smartf.comu.entity;

import com.smartf.comu.dto.PumpDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pumps")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pump {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Long number;

    @Column(name = "state")
    private Long state;

}
