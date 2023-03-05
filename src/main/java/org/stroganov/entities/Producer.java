package org.stroganov.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Producer {
    private String id;
    private String name;
    private String material;
    private String description;
}
