package tw.cchs.investsmartiqserver.web.user.dto;

import tw.cchs.investsmartiqserver.web.user.constant.Gender;

public class UserUpdateRequest {

    private String password;
    private String email;
    private Gender gender;
    private Integer changeId;

    public UserUpdateRequest() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getChangeId() {
        return changeId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

}
