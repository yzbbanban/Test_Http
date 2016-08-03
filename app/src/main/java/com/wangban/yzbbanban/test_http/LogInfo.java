package com.wangban.yzbbanban.test_http;

import java.io.Serializable;

/**
 * Created by YZBbanban on 16/8/3.
 */

public class LogInfo implements Serializable {
    private String compNo;
    private String userID;
    private String userPwd;
    private String checkKey;

    public String getCompNo() {
        return compNo;
    }

    public void setCompNo(String compNo) {
        this.compNo = compNo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }
}
