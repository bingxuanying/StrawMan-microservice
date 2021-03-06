package com.example.dataservice.controller;

import com.example.dataservice.models.DataModel;
import com.example.dataservice.payload.request.FetchRecordsRequest;
import com.example.dataservice.payload.request.SetBedTimeRequest;
import com.example.dataservice.payload.response.DataSetResponse;
import com.example.dataservice.payload.response.TrapIDsResponse;
import com.example.dataservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/fetchTrapIDs")
    public @ResponseBody
    TrapIDsResponse fetchTrapIDs(Authentication authentication) {
        System.out.println(authentication.getName());

        String username = authentication.getName();
        List<Integer> IDSet = userService.fetchTrapIDs(username);
        Collections.sort(IDSet);
        return new TrapIDsResponse(IDSet);
    }

    @PostMapping(value = "/fetchRecordsByID")
    public @ResponseBody
    DataSetResponse fetchRecordsByID(Authentication authentication, @RequestBody @Valid FetchRecordsRequest request) {
        String username = authentication.getName();
        Integer trapID = request.getId();

        List<Integer> IDSet = userService.fetchTrapIDs(username);

        if (!IDSet.contains(trapID)) {
            throw new BadCredentialsException("Unauthorized access to Trap #" + IDSet);
        }

        List<DataModel> dataSet = userService.fetchRecordsByID(trapID);

        return new DataSetResponse(dataSet);
    }

    @PostMapping(value = "/setBedTime")
    public boolean setBedTime(Authentication authentication, @RequestBody @Valid SetBedTimeRequest request) {
        String username = authentication.getName();
        Integer milisec = request.getMilisec();
        userService.setBedTime(username, milisec);
        return true;
    }
}
