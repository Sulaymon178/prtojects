package com.demo.controller;

import com.demo.entitiy.Country;
import com.demo.entitiy.Region;
import com.demo.entitiy.Users;
import com.demo.payload.ApiResponse;
import com.demo.payload.ReqCountry;
import com.demo.payload.ReqRegion;
import com.demo.payload.ReqUser;
import com.demo.repository.CountryRepository;
import com.demo.repository.RegionRepository;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/region")
public class RegionController {
    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public HttpEntity<?> getRegion() {
        try {
            List<Region> all = regionRepository.findAll();
            return ResponseEntity.ok(new ApiResponse("success", true, all));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @GetMapping("{id}")
    public HttpEntity<?> getRegionById(@PathVariable Integer id) {
        try {
            Optional<Region> byId = regionRepository.findById(id);
            return ResponseEntity.ok(new ApiResponse("success", true, byId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }

    }

    @PostMapping
    public HttpEntity<?> saveRegion(@RequestBody ReqRegion reqRegion) {
        try {
            Region save = regionRepository.save(new Region(reqRegion.getName(), countryRepository.findById(reqRegion.getCountryId()).get()));
            return ResponseEntity.ok(new ApiResponse("success", true, save));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }

    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteRegion(@PathVariable Integer id) {
        Optional<Region> byId = regionRepository.findById(id);
        if (byId.isPresent()) {
            Region region = byId.get();
//            countryRepository.deleteById(region.getCountry().getId());
            regionRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("success", true, region));
        } else {
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @PutMapping("{id}")
    public HttpEntity<?> editRegion(@PathVariable Integer id, @RequestBody ReqRegion reqRegion) {
        try {
            Region region = regionRepository.findById(id).get();

            region.setName(reqRegion.getName());
            Region save = regionRepository.save(region);
            return ResponseEntity.ok(new ApiResponse("success", true, save));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }
}
