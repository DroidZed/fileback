package com.wevioo.fileback.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wevioo.fileback.geolocationClasses.DisplayLatLng;
import com.wevioo.fileback.geolocationClasses.GeocodeResult;
import com.wevioo.fileback.geolocationClasses.Location;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GeoCoderService {

    public DisplayLatLng getAddressCoded(String addr)
            throws IOException
    {
        String encodedAddress =  URLEncoder.encode(addr, StandardCharsets.UTF_8);
        OkHttpClient client = new OkHttpClient();
        String key = "lAW53bjzi0nqhmmyOP9BZ0SJNdbyZfNi";
        Request request = new Request.Builder()
                .url("http://www.mapquestapi.com/geocoding/v1/address?key="
                        + key
                        + "&location="
                        + encodedAddress)
                .get()
                .build();

        ResponseBody responseBody =
                client
                        .newCall(request)
                        .execute()
                        .body();

        ObjectMapper objectMapper = new ObjectMapper();

        assert responseBody != null;

        GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);

        DisplayLatLng final_loc = new DisplayLatLng();

        List<Location> locationArray = result.results.get(0).locations;

        String cityFromAddress = addr.split(",")[1];
        String streetFromAddress = addr.split(",")[0];


        System.out.println("Locations ...\n");
        for(Location loc: locationArray)
        {
            if (loc.adminArea5.equals(cityFromAddress))
            {
                final_loc = loc.displayLatLng;
                System.out.println(final_loc);
            }
            System.out.println(loc.displayLatLng);
        }
        return final_loc;
    }
    
}
