package com.apple.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalCount {

    @Getter
    @Setter
    private float lastweek;

    @Getter
    @Setter
    private float yeartodate;
}
