package com.wevioo.fileback.geolocationClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "maxResults",
        "thumbMaps",
        "ignoreLatLngInput"
})
public class Options {

    @JsonProperty("maxResults")
    public Integer maxResults;
    @JsonProperty("thumbMaps")
    public Boolean thumbMaps;
    @JsonProperty("ignoreLatLngInput")
    public Boolean ignoreLatLngInput;

}
