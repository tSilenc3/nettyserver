package com.adward.netty.netty.logic.Common;

import com.alibaba.fastjson.annotation.JSONField;

public class ResponseData {
    private int cmd;

    private String err;

    @JSONField(ordinal = 1)
    private Object body;

    public ResponseData(int cmd, Object body) {
        this.cmd = cmd;
        this.body = body;
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

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String msg) {
        this.err = msg;
    }
}
