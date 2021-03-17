package com.wevioo.fileback.geolocationClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "street",
        "adminArea6",
        "adminArea6Type",
        "adminArea5",
        "adminArea5Type",
        "adminArea4",
        "adminArea4Type",
        "adminArea3",
        "adminArea3Type",
        "adminArea1",
        "adminArea1Type",
        "postalCode",
        "geocodeQualityCode",
        "geocodeQuality",
        "dragPoint",
        "sideOfStreet",
        "linkId",
        "unknownInput",
        "type",
        "latLng",
        "displayLatLng",
        "mapUrl"
})
public class Location {

    @JsonProperty("street")
    public String street;
    @JsonProperty("adminArea6")
    public String adminArea6;
    @JsonProperty("adminArea6Type")
    public String adminArea6Type;
    @JsonProperty("adminArea5")
    public String adminArea5;
    @JsonProperty("adminArea5Type")
    public String adminArea5Type;
    @JsonProperty("adminArea4")
    public String adminArea4;
    @JsonProperty("adminArea4Type")
    public String adminArea4Type;
    @JsonProperty("adminArea3")
    public String adminArea3;
    @JsonProperty("adminArea3Type")
    public String adminArea3Type;
    @JsonProperty("adminArea1")
    public String adminArea1;
    @JsonProperty("adminArea1Type")
    public String adminArea1Type;
    @JsonProperty("postalCode")
    public String postalCode;
    @JsonProperty("geocodeQualityCode")
    public String geocodeQualityCode;
    @JsonProperty("geocodeQuality")
    public String geocodeQuality;
    @JsonProperty("dragPoint")
    public Boolean dragPoint;
    @JsonProperty("sideOfStreet")
    public String sideOfStreet;
    @JsonProperty("linkId")
    public String linkId;
    @JsonProperty("unknownInput")
    public String unknownInput;
    @JsonProperty("type")
    public String type;
    @JsonProperty("latLng")
    public LatLng latLng;
    @JsonProperty("displayLatLng")
    public DisplayLatLng displayLatLng;
    @JsonProperty("mapUrl")
    public String mapUrl;

}
