package tw.cchs.investsmartiqserver.core.constant;

public enum Drop {

    X(1);

    private Integer value;

    private Drop(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
