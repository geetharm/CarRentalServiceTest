package com.apple.model;

import lombok.*;
import com.fasterxml.jackson.annotation.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {

    @Getter
    @Setter
    private String make;

    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private String vin;

    @Getter
    @Setter
    @JsonProperty("metadata")
    Metadata MetadataObject;

    @Getter
    @Setter
    @JsonProperty("perdayrent")
    Perdayrent PerdayrentObject;

    @Getter
    @Setter
    @JsonProperty("metrics")
    Metrics MetricsObject;
}