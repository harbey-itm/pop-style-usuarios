package co.com.popstyle.usuarios.service;

import java.util.List;

import co.com.popstyle.usuarios.dto.response.PerfilResponseDto;

public interface IPerfilService {

	List<PerfilResponseDto> obtenerRoles();
	
}
