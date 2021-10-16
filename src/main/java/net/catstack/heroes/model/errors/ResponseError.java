package net.catstack.heroes.model.errors;

public class ResponseError {
    private int status;
    private String error;

    public ResponseError(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
