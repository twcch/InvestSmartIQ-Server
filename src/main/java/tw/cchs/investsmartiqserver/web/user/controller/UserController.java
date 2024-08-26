package tw.cchs.investsmartiqserver.web.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.cchs.investsmartiqserver.core.util.Page;
import tw.cchs.investsmartiqserver.web.user.dto.UserLoginRequest;
import tw.cchs.investsmartiqserver.web.user.dto.UserQueryParams;
import tw.cchs.investsmartiqserver.web.user.dto.UserRegisterRequest;
import tw.cchs.investsmartiqserver.web.user.entity.User;
import tw.cchs.investsmartiqserver.web.user.service.UserService;

import java.util.List;

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

    @GetMapping("/users")
    public ResponseEntity<Page<?>> getUsers(
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset) {

        UserQueryParams userQueryParams = new UserQueryParams();
        userQueryParams.setOrderBy(orderBy);
        userQueryParams.setSort(sort);
        userQueryParams.setLimit(limit);
        userQueryParams.setOffset(offset);

        List<User> userList = userService.findAll(userQueryParams);

        Long total = userService.count();

        Page<User> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setData(userList);
        page.setTotal(total);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

}
