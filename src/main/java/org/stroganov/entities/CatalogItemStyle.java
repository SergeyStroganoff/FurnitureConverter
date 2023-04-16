package org.stroganov.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "catalog_item_style",
        uniqueConstraints = {@UniqueConstraint(name = "UniqueStyleArticleName", columnNames = {"style_article", "style_name"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CatalogItemStyle {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "style_article", nullable = false)
    private String styleArticle;

    @Column(name = "style_name", nullable = false)
    private String styleName;
}
