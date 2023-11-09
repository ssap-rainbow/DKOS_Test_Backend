package shop.ssap.ssap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomDuplicateKeyException.class)
    public ResponseEntity<?> handleCustomDuplicateKeyException(CustomDuplicateKeyException ex) {
        // 한국어로 사용자에게 메시지를 반환합니다.
        String errorMessage = "해당 제공자 ID는 이미 사용 중입니다: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

}
