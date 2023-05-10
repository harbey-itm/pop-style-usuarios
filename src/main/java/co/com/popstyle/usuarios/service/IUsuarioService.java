package co.com.popstyle.usuarios.service;


import co.com.popstyle.usuarios.dto.request.UsuarioRequestDto;
import co.com.popstyle.usuarios.dto.response.UsuarioResponseDto;

public interface IUsuarioService {
	
	boolean existeUsuario(Long idUsuario);
	UsuarioResponseDto getUsuarioEmail(String email);
	UsuarioResponseDto getUsuarioEmailDto(String email);
	UsuarioRequestDto guardarUsuario(UsuarioRequestDto usuarioDto);
	UsuarioRequestDto actualizarUsuario(UsuarioRequestDto usuarioDto);
}
