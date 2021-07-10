package com.demo.controller;

import com.demo.entitiy.AutoId;
import com.demo.entitiy.Region;
import com.demo.entitiy.Users;
import com.demo.payload.ApiResponse;
import com.demo.payload.ReqListUsers;
import com.demo.payload.ReqRegion;
import com.demo.payload.ReqUser;
import com.demo.repository.CountryRepository;
import com.demo.repository.RegionRepository;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public HttpEntity<?> getUsers() {
        try {
            List<Users> usersList = userRepository.findAll();
            return ResponseEntity.ok(new ApiResponse("success", true, usersList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @GetMapping("{id}")
    public HttpEntity<?> getUserById(@PathVariable Integer id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(new ApiResponse("success", true, user.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public HttpEntity<?> saveUser(@RequestBody ReqUser reqUser) {
        try {
            Users save = userRepository.save(new Users(reqUser.getName(), regionRepository.findById(reqUser.getRegionId()).get()));
            return ResponseEntity.ok(new ApiResponse("success", true, save));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteUserById(@PathVariable Integer id) {

        try {
            Users users = userRepository.findById(id).get();

//            regionRepository.deleteById(userRepository.findById(id).get().getRegion().getId());
            userRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("success", true, users));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
    }

    @PutMapping("{id}")
    public HttpEntity<?> editUser(@RequestBody ReqUser reqUser, ReqRegion reqRegion, @PathVariable Integer id) {
        try {
            Users user = userRepository.findById(id).get();
            user.setName(reqUser.getName());
            Users saveUser = userRepository.save(user);
            return ResponseEntity.ok(new ApiResponse("success", true, saveUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }

    }

    // obyektni ichma ich qilib yuborish
    @PostMapping("list")
    public HttpEntity<?> saveListUsers(@RequestBody ReqListUsers reqUserList) {
        try {
            List<Users> users = new ArrayList<>();
            for (ReqUser reqUsers : reqUserList.getReqUser()) {
                users.add(new Users(reqUsers.getName(), regionRepository.findById(reqUserList.getRegionId()).get()));
            }
            userRepository.saveAll(users);
            return ResponseEntity.ok(new ApiResponse("success", true, users));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }

        //postman ga body siga yozilishi;
        // {
        //   "reqUser":[{"name":"sulaymon1" },{"name":"sulaymon2" },{"name":"sulaymon3" },{"name":"sulaymon4" }]
        //}

    }


    @GetMapping("page")
    public HttpEntity<?> getPageUser(@RequestParam Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Users> pageUser = userRepository.findAll(pageable);
            return ResponseEntity.ok(new ApiResponse("success", true, pageUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("error", false, null));
        }
        // masalan page:0,size:2 bo`lsa birinchi betga 2 user olib boradi.
        // masalan page:1,size:3 bo`lsa ikkinch betga 3 user olib boradi.

    }
}
