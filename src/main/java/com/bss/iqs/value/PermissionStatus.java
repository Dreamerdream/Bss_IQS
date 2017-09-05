package com.bss.iqs.value;

public enum  PermissionStatus {
    OPEN("1"),CLOSE("0");
    private String val;
    PermissionStatus(String val){
        this.val = val;
    }
    public String val(){
        return this.val;
    }
}
