package co.com.popstyle.usuarios.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.popstyle.usuarios.dto.request.UsuarioRequestDto;
import co.com.popstyle.usuarios.dto.response.RespuestaDto;
import co.com.popstyle.usuarios.dto.response.RespuestaObjetosJson;
import co.com.popstyle.usuarios.dto.response.UsuarioResponseDto;
import co.com.popstyle.usuarios.service.IUsuarioService;

@RestController
public class UsuarioController<T> {

	@Autowired
	public IUsuarioService usuarioService;

	@GetMapping(value = "/getUsuarios")
	public String getUsuarios() {
		System.out.println("Ok Usuarios");
		
		int a = 5, b = 0;
		return a/b+"Ok Usuarios"; // return

	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getUsuarioEmail/{email}/")
	public RespuestaObjetosJson<T> getUsuarioEmail(@PathVariable String email) {

		UsuarioResponseDto usuarioDto = usuarioService.getUsuarioEmail(email);

		if (usuarioDto != null) {
			List<T> usuario = new ArrayList<T>();
			usuario.add((T) usuarioDto);

			return (new RespuestaObjetosJson<T>(new RespuestaDto(true, "", ""), usuario));
		}

		return (new RespuestaObjetosJson<T>(
				new RespuestaDto(false, "El usuario con email: " + email + " NO existe en la Base de Datos !!!", ""),
				null));

	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/recuperarPassword/{email}/")
	public RespuestaObjetosJson<T> recuperarPassword(@PathVariable String email) {

		UsuarioResponseDto usuarioDto = usuarioService.getRecuperarPassword(email);

		if (usuarioDto != null) {
			List<UsuarioResponseDto> usuarios = new ArrayList<UsuarioResponseDto>();
			usuarios.add(usuarioDto);
			return (new RespuestaObjetosJson<T>(
					new RespuestaDto(true,
							"Se reestabeció la contraseña del usuario con el correo: " + email + "correctamente", ""),
					(List<T>) usuarios));
		}
		return (new RespuestaObjetosJson<T>(new RespuestaDto(false,
				"No se pudo reestablecer la contraseña del usuario con el correo: " + email, ""), null));

	}

	@PostMapping(value = "/registrarUsuario")
	public RespuestaObjetosJson<T> registrarUsuario(@RequestBody @Valid UsuarioRequestDto usuarioDto) {

		UsuarioResponseDto buscarUsuario = usuarioService.getUsuarioEmail(usuarioDto.getEmail());

		if (buscarUsuario != null) {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(false,
					"No se puede completar el registro !!! El usuario con el correo electronico "
							+ usuarioDto.getEmail() + " ya existen en la BBDD",
					""), null));

		}

		usuarioDto = usuarioService.guardarUsuario(usuarioDto);
		return (new RespuestaObjetosJson<T>(
				new RespuestaDto(true, "El usuario se registró correctamente en la base de datos.", ""), null));

	}

	@PutMapping(value = "/actualizarUsuario")
	public RespuestaObjetosJson<T> actualizarUsuario(@RequestBody @Valid UsuarioRequestDto usuarioDto) {

		if (usuarioService.existeUsuario(usuarioDto.getIdUsuario())) {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(false,
					"El usuario " + usuarioDto.getIdUsuario() + " no existe en la base de datos.", ""), null));
		}

		if (usuarioService.actualizarUsuario(usuarioDto) != null) {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(true,
					"El usuario " + usuarioDto.getIdUsuario() + " se actualizó correctamente", ""), null));
		} else {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(false,
					"El usuario: " + usuarioDto.getIdUsuario() + " con email " + usuarioDto.getEmail()
							+ " no se pudo actualizar porque el correo: " + usuarioDto.getEmail()
							+ " ya esta asignado a otro usuario !!!",
					""), null));
		}
	}

}
