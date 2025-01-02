package org.openapitools.model;

public class ReceiptsIdPointsGet404Response {
    Integer status = 404;
    String error;
    String message;
    String path = "/receipts/{id}/points";

    public ReceiptsIdPointsGet404Response(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
