package com.smartf.comu.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reservoirs")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservoir {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "reservoir_name")
    private String reservoirName;

    @Column(name = "fuel_level")
    private Double fuelLevel;

    @Column(name = "fuel_max")
    private Double fuelMax;

}
