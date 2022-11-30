package vn.unicloud.notification.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.notification.enums.ResponseCode;

@Data
@NoArgsConstructor
public class ResponseData<T> {

    private int code;
    private String message;
    private T data;

    public ResponseData(T data) {
        this.data = data;
        this.message = ResponseCode.SUCCESS.getMessage();
        this.code = ResponseCode.SUCCESS.getCode();
    }

    public ResponseData(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public ResponseData(T data, int code, String message) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
