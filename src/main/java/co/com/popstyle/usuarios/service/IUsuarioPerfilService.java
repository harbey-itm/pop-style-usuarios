package co.com.popstyle.usuarios.service;

import co.com.popstyle.usuarios.dto.request.UsuarioPerfilRequestDto;

public interface IUsuarioPerfilService {

	boolean guardarPerfilUsuario(UsuarioPerfilRequestDto usuarioDto);

	boolean actualizarPerfilUsuario(UsuarioPerfilRequestDto usuarioDto);
	// UsuarioPerfilesResponseDto getUsuarioPerfil(Long codigo);

}
