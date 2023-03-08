package org.stroganov.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommonModel {
    private int id;
    private String article;
    private String description;
    private Type type;
    private Size size;
    private Producer producer;
}
