package com.cat233.flash_sale_server.model;

public class SuccessfulResponse<T> extends Response<T> {
	private T data;

    public SuccessfulResponse(String status, String code, String msg, T data) {
        super(status, code, msg);
        this.data = data;
    }
    
    public SuccessfulResponse(T data) {
        super("OK","10000", "success");
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
