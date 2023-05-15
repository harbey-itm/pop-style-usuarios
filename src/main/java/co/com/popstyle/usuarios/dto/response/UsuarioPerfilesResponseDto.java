package co.com.popstyle.usuarios.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPerfilesResponseDto {

	private String codigo;
	private String nombre;
	private String estado;
	private String nombreEstado;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fechaCreacion;

	private List<PerfilResponseDto> perfiles = new ArrayList<PerfilResponseDto>();

}
