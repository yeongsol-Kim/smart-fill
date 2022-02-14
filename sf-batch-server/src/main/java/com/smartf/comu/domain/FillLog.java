package com.smartf.comu.domain;

public class FillLog {
    private Long id;
    private Long userId;
    private Long datetime;
    private Long fillAmount;
    private Long gasPumpId;

    public FillLog(Long userId, Long datetime, Long fillAmount, Long gasPumpId) {
        this.userId = userId;
        this.datetime = datetime;
        this.fillAmount = fillAmount;
        this.gasPumpId = gasPumpId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public Long getFillAmount() {
        return fillAmount;
    }

    public void setFillAmount(Long fillAmount) {
        this.fillAmount = fillAmount;
    }

    public Long getGasPumpId() {
        return gasPumpId;
    }

    public void setGasPumpId(Long gasPumpId) {
        this.gasPumpId = gasPumpId;
    }
}
