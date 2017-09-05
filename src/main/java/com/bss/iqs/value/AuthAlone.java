package com.bss.iqs.value;

public enum  AuthAlone {

    OPEN("1"),CLOSE("0");
    private String val;
    AuthAlone(String val){
        this.val = val;
    }
    public String val(){
        return this.val;
    }
}
