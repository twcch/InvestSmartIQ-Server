package tw.cchs.investsmartiqserver.web.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tw.cchs.investsmartiqserver.core.util.Encryption;
import tw.cchs.investsmartiqserver.web.user.constant.Role;
import tw.cchs.investsmartiqserver.web.user.constant.Status;
import tw.cchs.investsmartiqserver.web.user.dto.UserLoginRequest;
import tw.cchs.investsmartiqserver.web.user.dto.UserQueryParams;
import tw.cchs.investsmartiqserver.web.user.dto.UserRegisterRequest;
import tw.cchs.investsmartiqserver.web.user.entity.User;
import tw.cchs.investsmartiqserver.web.user.repository.UserRepository;
import tw.cchs.investsmartiqserver.web.user.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        User existingUserName = userRepository.findByUsername(userRegisterRequest.getUsername());
        validateUsernameExists(existingUserName);

        if (userRegisterRequest.getEmail() != null) {
            User existingEmail = userRepository.findByEmail(userRegisterRequest.getEmail());
            validateEmailExists(existingEmail);
        }

        Encryption encryption = new Encryption();
        String hashedPassword = encryption.getHashPassword(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(hashedPassword);

        return createUser(userRegisterRequest);

    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        User user = userRepository.findByUsername(userLoginRequest.getUsername());

        validateUsernameNotExists(user);

        validateUserPassword(user, userLoginRequest.getPassword());

        return user;

    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(userRegisterRequest.getPassword());
        user.setEmail(userRegisterRequest.getEmail());
        user.setGender(userRegisterRequest.getGender());
        user.setStatus(Status.DISABLE);
        user.setDrop(null);
        user.setChangeId(Role.SYSTEM.getValue());

        Date now = new Date();
        user.setCreatedDate(now);
        user.setLastModifiedDate(now);

        User savedUser = userRepository.save(user);

        return savedUser.getUserId();

    }

    @Override
    public List<User> findAll(UserQueryParams userQueryParams) {

        Pageable pageable = PageRequest.of(
                userQueryParams.getOffset(),
                userQueryParams.getLimit(),
                Sort.by("createdDate").ascending()
        );

        return userRepository.findAll(pageable).getContent();

    }

    @Override
    public User findByUserId(Integer userId) {

        return userRepository.findByUserId(userId);

    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);

    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);

    }

    @Override
    public Long count() {

        return userRepository.count();

    }

    @Override
    public void deleteUserById(Integer userId) {

        userRepository.deleteById(userId);

    }

    private void validateUsernameExists(User user) {

        if (user != null) {
            log.warn("該 username {} 已經被註冊", user.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered.");
        }

    }

    private void validateUsernameNotExists(User user) {

        if (user == null) {
            log.warn("該 username {} 尚未註冊", user.getUsername());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not registered.");
        }

    }

    private void validateEmailExists(User user) {

        if (user != null) {
            log.warn("該 email {} 已經被註冊", user.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered.");
        }

    }

    private void validateEmailNotExists(User user) {

        if (user == null) {
            log.warn("該 email {} 尚未註冊", user.getEmail());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not registered.");
        }

    }

    private void validateUserPassword(User user, String password) {

        Encryption encryption = new Encryption();
        String hashedPassword = encryption.getHashPassword(password);

        if (!user.getPassword().equals(hashedPassword)) {
            log.warn("該 username {} 的密碼不正確", user.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password.");
        }

    }

}
