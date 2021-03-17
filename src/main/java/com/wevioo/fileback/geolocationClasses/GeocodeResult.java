package com.wevioo.fileback.geolocationClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "info",
        "options",
        "results"
})
public class GeocodeResult {

    @JsonProperty("info")
    public Info info;
    @JsonProperty("options")
    public Options options;
    @JsonProperty("results")
    public List<Result> results;
}


