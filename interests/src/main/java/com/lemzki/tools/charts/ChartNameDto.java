package com.lemzki.tools.charts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
/**
 * Flexible DTO for sending out either a list of chart names or a list of chart items
 */
public class ChartNameDto {
    private String name;
    private String uiName;
}
