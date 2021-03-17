package com.wevioo.fileback.geolocationClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "imageUrl",
        "imageAltText"
})
public class Copyright {

    @JsonProperty("text")
    public String text;
    @JsonProperty("imageUrl")
    public String imageUrl;
    @JsonProperty("imageAltText")
    public String imageAltText;

}
