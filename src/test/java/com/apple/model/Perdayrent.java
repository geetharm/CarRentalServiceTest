package com.apple.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Perdayrent {

    @Getter
    @Setter
    @JsonProperty("Price")
    private float price;

    @Getter
    @Setter
    @JsonProperty("Discount")
    private float discount;
}
