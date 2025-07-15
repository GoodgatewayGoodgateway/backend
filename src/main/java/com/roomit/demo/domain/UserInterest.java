package com.roomit.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_interests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // DB 컬럼명에 맞춤
    private User user;

    @Column(name = "interest", nullable = false, length = 100) // DB 컬럼 매핑
    private String name;
}
