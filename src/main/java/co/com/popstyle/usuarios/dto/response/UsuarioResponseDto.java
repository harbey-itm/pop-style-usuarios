package co.com.popstyle.usuarios.dto.response;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioResponseDto {

	private Long idUsuario;
	private String nombresApellidos;
	private String email;
	private String celular;
	private String estado;	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//private LocalDateTime fechaCreacion;	
	private Set<PerfilResponseDto> perfiles = new HashSet<PerfilResponseDto>(); 
}
