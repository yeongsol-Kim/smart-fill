package com.smartf.comu.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pumps")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pump {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "number")
    private Long number;

    @Column(name = "state")
    private Long state;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "reservoir_id")
    private Long reservoirId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "reservoir_id")
//    private Reservoir reservoir;

}
