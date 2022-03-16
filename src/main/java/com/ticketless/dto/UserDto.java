package com.ticketless.dto;

public class UserDto {

    private String userName;
    
    private String mobile;

    public UserDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserDto(String userName, String mobile) {
        super();
        this.userName = userName;
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserDto [userName=" + userName + ", mobile=" + mobile + "]";
    }
    
    
    
}
