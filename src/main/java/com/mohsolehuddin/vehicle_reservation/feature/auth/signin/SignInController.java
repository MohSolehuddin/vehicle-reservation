package com.mohsolehuddin.vehicle_reservation.feature.auth.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohsolehuddin.vehicle_reservation.constant.PathAPI;


@Controller
@RequestMapping(PathAPI.AUTH + PathAPI.SIGN_IN)
public class SignInController {
    @PostMapping()
    public String signin(@RequestBody String entity) {
        return entity;
    }
    
    
}
