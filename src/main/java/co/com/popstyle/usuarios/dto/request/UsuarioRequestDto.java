package co.com.popstyle.usuarios.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UsuarioRequestDto {

	private Long idUsuario;
	
	@NotBlank(message = "El campo: nombre no puede estar vacio")
	@Size(min=8, max = 255, message = "El campo: nombres debe tener un tamaño entre 8 y 255 caracteres")
	private String nombres;
	
	@NotBlank(message = "El campo: apellidos no puede estar vacio")
	@Size(min=8, max = 255, message = "El campo: apellidos debe tener un tamaño entre 8 y 255 caracteres")
	private String apellidos;
	
	@NotBlank(message = "El campo: estado no puede estar vacio")
	@Size(min=1, max = 5, message = "El campo: estado debe tener un tamaño entre 1 y 5 caracteres")
	private String estado;
	
	@NotBlank(message = "El campo: email no puede estar vacio")
	@Size(min=5, max = 255, message = "El campo: email debe tener un tamaño entre 5 y 255 caracteres")
	private String email;
	
	@NotBlank(message = "El campo: celular no puede estar vacio")
	@Size(min=10, max = 15, message = "El campo: celular debe tener un tamaño entre 10 y 15 caracteres")
	private String celular;
	
	@NotBlank(message = "El campo: password no puede estar vacio")
	@Size(min=8, max = 16, message = "El campo: password debe tener un tamaño entre 8 y 16 caracteres")
	private String password;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fechaCreacion;

}
