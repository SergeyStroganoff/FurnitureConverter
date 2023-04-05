package org.stroganov.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "size")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Size {
    @Id
    private String id;
    private String with;
    private String high;
    private String depth;
}
