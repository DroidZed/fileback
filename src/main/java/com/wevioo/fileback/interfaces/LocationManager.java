package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.Locations;
import com.wevioo.fileback.model.NeedLocation;

public interface LocationManager {

    void updateLocation(Long id, Locations loc);
    void updateNeedLocation(Long id, NeedLocation loc);
}
