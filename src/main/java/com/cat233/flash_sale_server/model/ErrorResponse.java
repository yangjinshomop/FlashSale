package com.cat233.flash_sale_server.model;

public class ErrorResponse<T> extends Response<T> {
	private String sub_code;
    private String sub_msg;

    public ErrorResponse(String status, String code, String msg, String sub_code, String sub_msg) {
        super(status, code, msg);
        this.sub_code = sub_code;
        this.sub_msg = sub_msg;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }
}
