package rs.ac.ni.pmf.webproject.laptopstore.models;

public enum Role {
    ADMIN(1, "ADMIN"),
    EMPLOYEE(2, "EMPLOYEE"),
    BUYER(3, "BUYER");

    private Integer value;
    private String code;

    Role(int value, String code) {
        this.value = value;
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Integer getValueByCode(String code){
        for (Role role: Role.values()) {
            if(role.getCode().equalsIgnoreCase(code)){
                return role.getValue();
            }
        }
        return null;
    }

    public static String getCodeByValue(Integer value){
        for (Role role: Role.values()) {
            if(role.getValue() == value){
                return role.getCode();
            }
        }
        return null;
    }
}
