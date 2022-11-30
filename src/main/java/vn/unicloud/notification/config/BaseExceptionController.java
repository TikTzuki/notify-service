package vn.unicloud.notification.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.unicloud.notification.core.HttpResponse;
import vn.unicloud.notification.core.ResponseData;
import vn.unicloud.notification.exception.InternalException;

@RestControllerAdvice
public class BaseExceptionController {
    private static final Logger log = LoggerFactory.getLogger(BaseExceptionController.class);

    public BaseExceptionController() {
    }

    @ExceptionHandler({InternalException.class})
    public ResponseEntity<?> handleBusinessException(InternalException e) {
        log.error("Business error {}", e.getMessage());
        return new HttpResponse<>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("", e);
        return HttpResponse
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseData<>(1, e.getMessage()));
    }

//    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseBase<FieldRequestError> handleArgumentInvalidException(BindException e) {
//        List<FieldRequestError.Error> errors = new ArrayList<>();
//        e.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.add(new FieldRequestError.Error(fieldName, errorMessage));
//        });
//        log.error("Invalid argument: {}", errors);
//        return new ResponseBase(
//                new FieldRequestError(errors),
//                ResponseCode.INVALID_PARAM.getCode(),
//                ResponseCode.INVALID_PARAM.getMessage()
//        );
//    }
//
//    @ExceptionHandler({PropertyReferenceException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseBase handlePropertyReferenceException(PropertyReferenceException e) {
//        return new ResponseBase(
//                new FieldRequestError.Error(e.getPropertyName(), String.format("Field %s is not accepted", e.getPropertyName())),
//                ResponseCode.INVALID_PARAM.getCode(),
//                ResponseCode.INVALID_PARAM.getMessage()
//        );
//    }
}
