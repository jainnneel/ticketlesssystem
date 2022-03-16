package com.ticketless.requestdto;

public class AuthMobileDto {

    private String mobile;

    public AuthMobileDto() {
        super();
    }

    public AuthMobileDto(String mobile) {
        super();
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "AuthMobileDto [mobile=" + mobile + "]";
    }
    
    
}
