package vn.unicloud.notification.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.unicloud.notification.enums.ResponseCode;

public class HttpResponse<T> extends ResponseEntity<ResponseData<T>> {

    public HttpResponse(T data) {
        this(data, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public HttpResponse(int code, String message) {
        this(null, code, message);
    }

    public HttpResponse(T data, int code, String message) {
        super(new ResponseData<T>(data, code, message), HttpStatus.OK);
    }

    public static <T> BodyBuilder<T> message(String message) {
        BodyBuilder<T> builder = new BodyBuilder<>();
        builder.message = message;
        return builder;
    }

    public static class BodyBuilder<T> {
        String message;
        int code;
        T data;

        public BodyBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public HttpResponse<T> build() {
            return new HttpResponse<>(data, code, message);
        }
    }

}
