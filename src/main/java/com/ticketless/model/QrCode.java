package com.ticketless.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QrCode {

    @Id
    private String qrId;
    
    private String qrUrl;

    public QrCode() {
        super();
    }

    public QrCode(String qrId, String qrUrl) {
        super();
        this.qrId = qrId;
        this.qrUrl = qrUrl;
    }

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    @Override
    public String toString() {
        return "QrCode [qrId=" + qrId + ", qrUrl=" + qrUrl + "]";
    }
    
}
