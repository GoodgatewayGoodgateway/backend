package com.roomit.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "option_values")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private OptionCategory category;

    @Column(nullable = false, length = 100)
    private String label;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "optionValue", cascade = CascadeType.ALL)
    private List<UserSelectedOption> selectedOptions;
}