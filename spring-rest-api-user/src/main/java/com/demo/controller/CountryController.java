package com.demo.controller;

import com.demo.entitiy.Country;
import com.demo.payload.ApiResponse;
import com.demo.payload.ReqCountry;
import com.demo.payload.ReqCountryList;
import com.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public HttpEntity<?> getCountry() {
        try {
            List<Country> allCountry = countryRepository.findAll();
            return ResponseEntity.ok(new ApiResponse("success", true, allCountry));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @GetMapping("{id}")
    public HttpEntity<?> getCountryById(@PathVariable Integer id) {
        Optional<Country> byId = countryRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(new ApiResponse("success", true, byId.get()));
        } else {
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @PostMapping
    public HttpEntity<?> saveCountry(@RequestBody ReqCountry reqCountry) {
        try {
            Country save = countryRepository.save(new Country(reqCountry.getName()));
            return ResponseEntity.ok(new ApiResponse("success", true, save));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ApiResponse("error", false, null));
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteCountry(@PathVariable Integer id) {
        try {
            Country country = countryRepository.findById(id).get();
            countryRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("success", true, country));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @PutMapping("{id}")
    public HttpEntity<?> editCountry(@PathVariable Integer id, @RequestBody ReqCountry reqCountry) {
        try {
            Country country = countryRepository.findById(id).get();
            country.setName(reqCountry.getName());
            Country save = countryRepository.save(country);
            return ResponseEntity.ok(new ApiResponse("success", true, save));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @PostMapping("list")
    public HttpEntity<?> saveListCountry(@RequestBody ReqCountryList reqCountryList) {

        try {
            List<Country> countries = new ArrayList<>();
            for (ReqCountry reqCountry : reqCountryList.getReqCountry()) {
                countries.add(new Country(reqCountry.getName()));
            }
            List<Country> saveAll = countryRepository.saveAll(countries);

            return ResponseEntity.ok(new ApiResponse("success", true, saveAll));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }
}
