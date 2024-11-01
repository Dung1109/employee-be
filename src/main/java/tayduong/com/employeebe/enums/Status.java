package tayduong.com.employeebe.enums;

public enum Status {

    ACTIVE(0),
    INACTIVE(1);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
