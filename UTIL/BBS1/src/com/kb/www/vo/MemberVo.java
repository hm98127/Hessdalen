package com.kb.www.vo;

public class MemberVo {
    private int sq;
    private String id;
    private String pwd;
    private boolean lgn_fl;
    private boolean leave_fl;

    public int getSq() {
        return sq;
    }

    public void setSq(int sq) {
        this.sq = sq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isLgn_fl() {
        return lgn_fl;
    }

    public void setLgn_fl(boolean lgn_fl) {
        this.lgn_fl = lgn_fl;
    }

    public boolean isLeave_fl() {
        return leave_fl;
    }

    public void setLeave_fl(boolean leave_fl) {
        this.leave_fl = leave_fl;
    }
}
