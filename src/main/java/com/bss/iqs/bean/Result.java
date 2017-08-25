package com.bss.iqs.bean;

public class Result {
    private int errorCode; //0表示成功，1表示失败
    private String errorReason; //原因


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
}
