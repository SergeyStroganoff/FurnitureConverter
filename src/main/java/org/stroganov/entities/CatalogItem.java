package org.stroganov.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CatalogItem {
    @Id
    @Column(name = "item_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "manufacture_id")
    private Manufacture producer;

    @Column(name = "style_name")
    private String styleName;

    @Column(name = "style_article")
    private String styleArticle;

    @Column(name = "price")
    private double price;
}
