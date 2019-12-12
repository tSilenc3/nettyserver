package com.adward.netty.netty.logic.Common;

import com.alibaba.fastjson.annotation.JSONField;

public class ResponseData {
    private String cmd;

    private String err;

    @JSONField(ordinal = 1)
    private Object body;

    public ResponseData(String cmd) {
        this.cmd = cmd;
    }

    public ResponseData(String cmd, Object body) {
        this.cmd = cmd;
        this.body = body;
    }

    public ResponseData(String cmd, String errMsg) {
        this.cmd = cmd;
        this.err = errMsg;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
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
