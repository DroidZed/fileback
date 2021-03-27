package com.wevioo.fileback.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.wevioo.fileback.geolocationClasses.DisplayLatLng;
import com.wevioo.fileback.geolocationClasses.GeocodeResult;
import com.wevioo.fileback.geolocationClasses.Location;

import com.wevioo.fileback.interfaces.GeoCoder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class GeoCoderService implements GeoCoder {

    private final RestTemplate restTemplate;

    public GeoCoderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<DisplayLatLng> getAddressCoded(String addr)
    {
        String encodedAddress =  URLEncoder.encode(addr, StandardCharsets.UTF_8);

        String key = "lAW53bjzi0nqhmmyOP9BZ0SJNdbyZfNi";

        String api = "http://www.mapquestapi.com/geocoding/v1/address?key="
                + key
                + "&location="
                + encodedAddress
                + "thumbMaps=false";


        ResponseEntity<GeocodeResult> response = this.restTemplate.getForEntity(api, GeocodeResult.class);

        GeocodeResult responseBody = null;

        if(response.getStatusCode() == HttpStatus.OK)
        {
           responseBody = response.getBody();
        }

        DisplayLatLng final_loc = new DisplayLatLng();

        assert responseBody != null;
        List<Location> locationArray = responseBody.results.get(0).locations;

        String cityFromAddress = addr.split(",")[1];
        String StateFromAddress = addr.split(",")[2];

        System.out.println("Locations ...\n");
        for(Location loc: locationArray)
        {
            if (loc.adminArea5.equals(cityFromAddress) || loc.adminArea5.equals(StateFromAddress))
            {
                final_loc = loc.displayLatLng;
            }
        }
        return CompletableFuture.completedFuture(final_loc);
    }
    
}
