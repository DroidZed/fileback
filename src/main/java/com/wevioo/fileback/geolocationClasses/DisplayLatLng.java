package com.wevioo.fileback.geolocationClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lat",
        "lng"
})
public class DisplayLatLng {

    @JsonProperty("lat")
    public Float lat;
    @JsonProperty("lng")
    public Float lng;

    @Override
    public String toString() {
        return "DisplayLatLng{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
