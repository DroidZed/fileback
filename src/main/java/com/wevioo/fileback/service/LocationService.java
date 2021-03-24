package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.LocationNotFoundException;
import com.wevioo.fileback.interfaces.LocationManager;
import com.wevioo.fileback.model.Locations;
import com.wevioo.fileback.model.NeedLocation;
import com.wevioo.fileback.repository.LocationsRepository;

import com.wevioo.fileback.repository.NeedsLocationRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationService implements LocationManager {
    
    private final LocationsRepository locationRepository;

    private final NeedsLocationRepository needsLocationRepository;

    public void updateLocation(Long id, Locations loc)
    {
       this.locationRepository.findById(id)
            .map(
                old -> {
                    old.setLatitude(loc.getLatitude());
                    old.setLongitude(loc.getLongitude());
                    return this.locationRepository.save(old);
                }
            )
            .orElseThrow(() -> new LocationNotFoundException(id));
    }

    public void updateNeedLocation(Long id, NeedLocation loc)
    {
        this.needsLocationRepository.findById(id)
                .map(
                        old -> {
                            old.setLat(loc.getLat());
                            old.setLng(loc.getLng());

                            return this.needsLocationRepository.save(old);
                        }
                )
                .orElseThrow(() -> new LocationNotFoundException(id));
    }
}
