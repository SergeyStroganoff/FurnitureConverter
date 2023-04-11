package org.stroganov.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "model")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Model {
    @Id
    @Column(name = "model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "article",unique = true)
    private String article;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "size_id")
    private Size size;

}
