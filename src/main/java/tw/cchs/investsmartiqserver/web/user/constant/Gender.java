package tw.cchs.investsmartiqserver.web.user.constant;

public enum Gender {

    FEMALE(0),
    MALE(1);

    private Integer value;

    private Gender(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
