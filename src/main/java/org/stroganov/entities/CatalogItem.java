package org.stroganov.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "catalog_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CatalogItem {

    @Id
    @Column(name = "catalog_item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Immutable
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "model_id")
    private Model model;

    @Immutable
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "manufacture_id")
    private Manufacture producer;

    @Immutable
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "catalog_item_style_id", referencedColumnName = "id")
    private CatalogItemStyle catalogItemStyle;

    @Column(name = "price")
    private double price;
}
