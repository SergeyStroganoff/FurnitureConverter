package org.stroganov.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "size")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Size {
    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "with")
    private String with;
    @Column(name = "hight")
    private String high;
    @Column(name = "depth")
    private String depth;
}
