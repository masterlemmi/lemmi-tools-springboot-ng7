package com.lemzki.tools.dev.plugins;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IdePlugin {
    private String name;
    private String link;
    private String image;
    private String description;
}
