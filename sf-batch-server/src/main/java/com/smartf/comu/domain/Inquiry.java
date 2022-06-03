package com.smartf.comu.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "type")
    private String type;

    @Column(name = "writer")
    private String writer;

    @Column(name = "inquiry_time")
    private LocalDateTime inquiryTime;
}
