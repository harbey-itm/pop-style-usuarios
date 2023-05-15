package co.com.popstyle.usuarios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.popstyle.usuarios.dto.request.UsuarioPerfilRequestDto;
import co.com.popstyle.usuarios.dto.response.RespuestaDto;
import co.com.popstyle.usuarios.dto.response.RespuestaObjetosJson;
import co.com.popstyle.usuarios.service.IUsuarioPerfilService;

@RestController
public class UsuarioPerfilController<T> {

	
	@Autowired
	private IUsuarioPerfilService usuarioPerfilService;
	
	@PostMapping(value = "/guardarPerfilUsuario")
	public RespuestaObjetosJson<T> guardarPerfilUsuario(@RequestBody @Valid UsuarioPerfilRequestDto usuarioPerfil) {

		if (usuarioPerfilService.guardarPerfilUsuario(usuarioPerfil)) {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(true,
					"Se asignó perfil " + usuarioPerfil.getIdPerfil() + " al usuario: " + usuarioPerfil.getIdUsuario(),
					"N/A"), null));
		}
		return (new RespuestaObjetosJson<T>(
				new RespuestaDto(false, "No se puede relacionar el usuario con el perfil, debido a que deben existir en la BBDD: "
						+ usuarioPerfil.getIdUsuario() + " y perfil: " + usuarioPerfil.getIdPerfil(), "N/A"),
				null));

	}
	@PutMapping(value = "/actualizarPerfilUsuario")
	public RespuestaObjetosJson<T> actualizarPerfilUsuario(@RequestBody @Valid UsuarioPerfilRequestDto usuarioPerfil) {

		if (usuarioPerfilService.actualizarPerfilUsuario(usuarioPerfil)) {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(true,
					"Se actualizó el estado del perfil " + usuarioPerfil.getIdPerfil() + " relacionado con el usuario: " + usuarioPerfil.getIdUsuario(),
					"N/A"), null));
		}
		return (new RespuestaObjetosJson<T>(
				new RespuestaDto(false, "No se puede actualizar el usuario con el perfil, debido a que debe existir la relacion en la BBDD usuario: "
						+ usuarioPerfil.getIdUsuario() + " y perfil: " + usuarioPerfil.getIdPerfil(), "N/A"),
				null));

	}
}
