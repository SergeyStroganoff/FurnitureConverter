package org.stroganov.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Services {
    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "cost")
    private Double cost;
}
