package com.smartf.comu.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry_request")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InquiryRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Column(name = "inquiry_id")
//    private Long inquiryId;

    @Column(name = "contents")
    private String contents;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @ManyToOne
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;
}
