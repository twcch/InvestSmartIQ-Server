package tw.cchs.investsmartiqserver.web.user.constant;

public enum Role {

    SYSTEM(0),
    ADMIN(1);

    private Integer value;

    private Role(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
