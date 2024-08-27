package tw.cchs.investsmartiqserver.web.user.service;

import tw.cchs.investsmartiqserver.web.user.dto.UserLoginRequest;
import tw.cchs.investsmartiqserver.web.user.dto.UserQueryParams;
import tw.cchs.investsmartiqserver.web.user.dto.UserRegisterRequest;
import tw.cchs.investsmartiqserver.web.user.entity.User;

import java.util.List;

public interface UserService {

    public Integer register(UserRegisterRequest userRegisterRequest);

    public User login(UserLoginRequest userLoginRequest);

    public Integer createUser(UserRegisterRequest userRegisterRequest);

    public List<User> findAll(UserQueryParams userQueryParams);

    public User findByUserId(Integer userId);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public Long count();

    public void deleteUserById(Integer userId);

}
