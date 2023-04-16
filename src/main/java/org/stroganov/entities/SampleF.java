package org.stroganov.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Entity reflecting furniture models from the SampleF catalog
 * of the 2020 Design program
 */


@Entity
@Table(name = "samplef")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SampleF {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "article", length = 15)
    private String article;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "model_id") //foreign key is always here.
    private Model model;
}

