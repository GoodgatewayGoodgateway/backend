package com.roomit.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_selected_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSelectedOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfile profile;

    @ManyToOne
    @JoinColumn(name = "option_value_id", nullable = false)
    private OptionValue optionValue;
}
