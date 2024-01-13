package com.rateuni.backend.models.request_response.request;

public class UserRequestProcess {
    private int userId;

    private int requestId;

    private String status;

    public UserRequestProcess() {

    }

    public UserRequestProcess(int userId, int requestId, String status) {
        this.userId = userId;
        this.requestId = requestId;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
