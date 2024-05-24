package org.elgordogato.supercalculator.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ElementCollection(targetClass = Double.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "numbers", joinColumns = @JoinColumn(name = "calculation_id"))
    @Column(name = "number", nullable = false)
    @Builder.Default
    private List<Double> numbers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Operation operation;

    private Double result;

    @Builder.Default
    private Instant timestamp = Instant.now();
}
