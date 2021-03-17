package com.wevioo.fileback.service;

import java.util.List;

import com.wevioo.fileback.exceptions.LocationNotFoundException;
import com.wevioo.fileback.model.Locations;
import com.wevioo.fileback.repository.LocationsRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationService {
    
    private final LocationsRepository locationRepository;

    public List<Locations> gatherAllLocations()
    {
        return this.locationRepository.findAll();
    }

    public Locations collectOneLocation(Long locId)
    {
        return this.locationRepository
                        .findById(locId)
                        .orElseThrow(() -> new LocationNotFoundException(locId));
    }
}