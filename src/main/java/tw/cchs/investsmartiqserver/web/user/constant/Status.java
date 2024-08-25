package tw.cchs.investsmartiqserver.web.user.constant;

public enum Status {

    DISABLE(0),
    ENABLE(1);

    private Integer value;

    private Status(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
