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
    @Column(name = "width")
    private String width;
    @Column(name = "height")
    private String height;
    @Column(name = "depth")
    private String depth;
}
