package com.ticketless.resposedto;

public class QrResponseDto {
    private String qrUrl;

    public QrResponseDto() {
        super();
    }

    public QrResponseDto(String qrUrl) {
        super();
        this.qrUrl = qrUrl;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    @Override
    public String toString() {
        return "QrResponseDto [qrUrl=" + qrUrl + "]";
    }
}
