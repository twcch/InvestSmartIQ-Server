package tw.cchs.investsmartiqserver.web.user.service;

import tw.cchs.investsmartiqserver.web.user.dto.UserRegisterRequest;
import tw.cchs.investsmartiqserver.web.user.entity.User;

public interface UserService {

    public Integer register(UserRegisterRequest userRegisterRequest);

    public Integer createUser(UserRegisterRequest userRegisterRequest);

    public User findByUserId(Integer userId);

    public User findByUsername(String username);

    public User findByEmail(String email);

}
