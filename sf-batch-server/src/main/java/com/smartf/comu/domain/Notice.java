package com.smartf.comu.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "notice")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "write_date")
    private String writeDate;

}
