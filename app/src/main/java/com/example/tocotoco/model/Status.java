package com.example.tocotoco.model;

public enum Status {
    DANG_KIEM_TRA("Đang kiểm tra"), DANG_CHUAN_BI("Đang chuẩn bị"), DA_HOAN_THANH("Đã hoàn thành");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
