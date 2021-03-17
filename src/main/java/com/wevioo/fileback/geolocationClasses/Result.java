package com.wevioo.fileback.geolocationClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "providedLocation",
        "locations"
})
public class Result {

    @JsonProperty("providedLocation")
    public ProvidedLocation providedLocation;
    @JsonProperty("locations")
    public List<Location> locations = null;

}
