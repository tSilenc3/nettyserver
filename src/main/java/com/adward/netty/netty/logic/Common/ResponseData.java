package com.adward.netty.netty.logic.Common;

public class ResponseData {
    private int cmd;

    private String err;

    private Object data;

    public ResponseData(int cmd, Object data) {
        this.cmd = cmd;
        this.data = data;
    }

    public ResponseData(int cmd, String errMsg) {
        this.cmd = cmd;
        this.err = errMsg;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String msg) {
        this.err = msg;
    }
}
