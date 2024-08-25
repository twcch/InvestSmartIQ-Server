package tw.cchs.investsmartiqserver.web.user.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.cchs.investsmartiqserver.web.user.dto.UserLoginRequest;
import tw.cchs.investsmartiqserver.web.user.dto.UserRegisterRequest;
import tw.cchs.investsmartiqserver.web.user.entity.User;
import tw.cchs.investsmartiqserver.web.user.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {

        Integer userId = userService.register(userRegisterRequest);

        User user = userService.findByUserId(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {

        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {

        User user = userService.findByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

}
