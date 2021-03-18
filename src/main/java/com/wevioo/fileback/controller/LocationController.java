package com.wevioo.fileback.controller;

import java.util.List;

import com.wevioo.fileback.model.Locations;
import com.wevioo.fileback.service.LocationService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/locations")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping(path = "/get")
    @ResponseBody
    public List<Locations> getAll()
    {
        return this.locationService.gatherAllLocations();
    }

    @GetMapping(path = "/get/{id}")
    @ResponseBody
    public Locations getOne(@PathVariable Long id)
    {
        return this.locationService.collectOneLocation(id);
    }

    @GetMapping(path = "/get/users")
    @ResponseBody
    public List<Locations> getLocationsOfUsers()
    {
        return this.locationService.getLocationsOfUsers();
    }

    @GetMapping(path = "/get/jobbers")
    @ResponseBody
    public List<Locations> getLocationsOfJobbers()
    {
        return this.locationService.getLocationsOfJobbers();
    }
}
