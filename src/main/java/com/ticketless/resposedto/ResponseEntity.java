package com.ticketless.resposedto;

import org.springframework.http.HttpStatus;

public class ResponseEntity {

    private Object data;
    private Exception exception;
    private String status;
    private HttpStatus httpStatus;
    private String token;
    
    public ResponseEntity() {
        super();
    }
    public ResponseEntity(Object data, Exception exception, String status, HttpStatus httpStatus, String token) {
        super();
        this.data = data;
        this.exception = exception;
        this.status = status;
        this.httpStatus = httpStatus;
        this.token = token;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Exception getException() {
        return exception;
    }
    public void setException(Exception exception) {
        this.exception = exception;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public String toString() {
        return "ResponseBody [data=" + data + ", exception=" + exception + ", status=" + status + ", httpStatus="
                + httpStatus + ", token=" + token + "]";
    }
    
    
    
}
