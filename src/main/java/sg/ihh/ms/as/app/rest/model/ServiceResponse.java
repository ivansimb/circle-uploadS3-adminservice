package sg.ihh.ms.as.app.rest.model;

import org.springframework.http.HttpStatus;

public class ServiceResponse {

    private int code;
    private String message;

    public ServiceResponse(HttpStatus status) {
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
