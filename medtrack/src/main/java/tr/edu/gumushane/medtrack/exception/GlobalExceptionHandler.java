package tr.edu.gumushane.medtrack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex, WebRequest request) {
		ErrorResponse response = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				"Not Found",
				ex.getMessage(),
				request.getDescription(false)
		);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, WebRequest request) {
		ErrorResponse response = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error",
				ex.getMessage(),
				request.getDescription(false)
		);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

