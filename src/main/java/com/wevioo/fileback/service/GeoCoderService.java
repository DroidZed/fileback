package com.wevioo.fileback.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wevioo.fileback.geolocationClasses.DisplayLatLng;
import com.wevioo.fileback.geolocationClasses.GeocodeResult;
import com.wevioo.fileback.geolocationClasses.Location;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GeoCoderService {

    @Async
    public CompletableFuture<DisplayLatLng> getAddressCoded(String addr)
            throws IOException
    {
        String encodedAddress =  URLEncoder.encode(addr, StandardCharsets.UTF_8);
        OkHttpClient client = new OkHttpClient();
        String key = "lAW53bjzi0nqhmmyOP9BZ0SJNdbyZfNi";
        Request request = new Request.Builder()
                .url("http://www.mapquestapi.com/geocoding/v1/address?key="
                        + key
                        + "&location="
                        + encodedAddress
                        + "thumbMaps=false"
                )
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
