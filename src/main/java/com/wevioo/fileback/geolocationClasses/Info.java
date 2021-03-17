package com.wevioo.fileback.geolocationClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "statuscode",
        "copyright",
        "messages"
})
public class Info {

    @JsonProperty("statuscode")
    public Integer statuscode;
    @JsonProperty("copyright")
    public Copyright copyright;
    @JsonProperty("messages")
    public List<Object> messages = null;

}
