package aula_hadlers.handler;

import jakarta.annotation.Resource;
import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    // Método para configurar os cabeçalhos da resposta
    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    // Método para criar uma resposta de erro
    private ResponseError responseError(String message, HttpStatus statusCode) {
        ResponseError responseError = new ResponseError();
        responseError.setStatus("error");
        responseError.setMessage(message); // Adicionando a mensagem corretamente
        responseError.setStatusCode(statusCode.value());
        return responseError;
    }

    // Tratamento de exceção geral
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)) {
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            return handleBusinessException((BusinessException) exception.getUndeclaredThrowable(), request);
        }
         else {
            // Recupera a mensagem de erro do arquivo de propriedades do Spring
            String message =messageSource.getMessage("error.server", new Object[]{e.getMessage()}, Locale.getDefault());
            ResponseError error = responseError(message, HttpStatus.INTERNAL_SERVER_ERROR);
            return handleExceptionInternal(e, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    // Tratamento para BusinessException
    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
        // Criando resposta de erro com código 409 (Conflict)
        ResponseError error = responseError(e.getMessage(), HttpStatus.CONFLICT);
        return handleExceptionInternal(e, error, headers(), HttpStatus.CONFLICT, request);
    }
}
