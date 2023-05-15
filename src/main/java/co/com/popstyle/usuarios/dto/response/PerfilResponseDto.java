package co.com.popstyle.usuarios.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponseDto {
	
	private Long idPerfil;
	private String nombrePerfil;
	private String descripcion;
	private String estado;
	//private Set<UsuarioResponseDto> usuarios = new HashSet<UsuarioResponseDto>();

}
