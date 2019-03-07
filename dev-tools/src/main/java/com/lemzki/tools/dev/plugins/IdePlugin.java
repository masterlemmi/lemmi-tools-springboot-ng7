package com.lemzki.tools.dev.plugins;

import com.lemzki.tools.dev.Ide;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="PLUGIN")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IdePlugin {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Ide ide;
    private String name;
    private String link;
    private String image;
    private String description;
}
