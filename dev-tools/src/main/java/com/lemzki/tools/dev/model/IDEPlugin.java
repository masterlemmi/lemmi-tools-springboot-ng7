package com.lemzki.tools.dev.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IDEPlugin {
    private String name;
    private String link;
    private String image;
    private String description;
}
