package org.stroganov.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dimension")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dimension {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "width")
    private String width;
    @Column(name = "height")
    private String height;
    @Column(name = "depth")
    private String depth;
}
