package com.apple.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metrics {

    @Getter
    @Setter
    private float yoymaintenancecost;

    @Getter
    @Setter
    private float depreciation;

    @Getter
    @Setter
    @JsonProperty("rentalcount")
    RentalCount RentalcountObject;
}
