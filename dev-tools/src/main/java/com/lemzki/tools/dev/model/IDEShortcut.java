package com.lemzki.tools.dev.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IDEShortcut {
    private String eclipse;
    private String intelliJ;
    private String vsCode;
    private String description;
}
