package co.com.popstyle.usuarios.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPerfilRequestDto {

	@NotNull(message = "El campo: idPerfil no puede ser nulo")
	private Long idPerfil;

	@NotNull(message = "El campo: idUsuario no puede ser nulo")
	private Long idUsuario;

	@NotBlank(message = "El campo: estado no puede estar vacio")
	@Size(min = 1, max = 5, message = "El estado debe tener un tamaño entre 1 y 5 caracteres")
	private String estado;

}
