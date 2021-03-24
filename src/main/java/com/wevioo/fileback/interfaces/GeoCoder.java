package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.geolocationClasses.DisplayLatLng;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface GeoCoder {

    CompletableFuture<DisplayLatLng> getAddressCoded(String addr) throws IOException;
}
