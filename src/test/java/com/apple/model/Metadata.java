package com.apple.model;

import lombok.*;
import com.fasterxml.jackson.annotation.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metadata {

    @Getter
    @Setter
    @JsonProperty("Color")
    private String color;

    @Getter
    @Setter
    @JsonProperty("Notes")
    private String notes;
}
