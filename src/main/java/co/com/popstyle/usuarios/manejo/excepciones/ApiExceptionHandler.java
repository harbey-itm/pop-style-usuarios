package co.com.popstyle.usuarios.manejo.excepciones;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.popstyle.usuarios.dto.response.RespuestaDto;
import co.com.popstyle.usuarios.dto.response.RespuestaObjetosJson;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public <T> RespuestaObjetosJson<T> internalServerError(HttpServletRequest request, Exception excepcion) {
		
		return new RespuestaObjetosJson<T>(new RespuestaDto(false, "Excepcion: "+excepcion.getClass().getSimpleName()+" Descripcion: "+ excepcion.getMessage(), "500"), null );
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoFoundException.class)
	@ResponseBody
	public <T> RespuestaObjetosJson<T> noFoundRequest(HttpServletRequest request, Exception excepcion) {
		
		return new RespuestaObjetosJson<T>(new RespuestaDto(false, "Excepcion: "+excepcion.getClass().getSimpleName()+" Descripcion: "+ excepcion.getMessage(), "404"), null );
	}

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
             
		RespuestaDto respuestaDto = new RespuestaDto(false,"El objeto no cumple con las validaciones","01");
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("respuesta", respuestaDto);
        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(x -> x.getDefaultMessage())
            .collect(Collectors.toList());
         
        responseBody.put("errors", errors);
         
        //return new RespuestaObjetosJson<T>(new RespuestaDto(false, "Excepcion: "+excepcion.getClass().getSimpleName()+" Descripcion: "+ excepcion.getMessage(), ""+status), null );
        return new ResponseEntity<>(responseBody, headers, status);
    }

}
