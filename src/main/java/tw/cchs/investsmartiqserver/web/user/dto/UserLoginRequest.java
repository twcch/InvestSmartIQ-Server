package tw.cchs.investsmartiqserver.web.user.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UserLoginRequest() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
