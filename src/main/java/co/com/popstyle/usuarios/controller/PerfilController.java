package co.com.popstyle.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.popstyle.usuarios.dto.response.PerfilResponseDto;
import co.com.popstyle.usuarios.dto.response.RespuestaDto;
import co.com.popstyle.usuarios.dto.response.RespuestaObjetosJson;
import co.com.popstyle.usuarios.service.IPerfilService;

@RestController
public class PerfilController<T> {

	@Autowired
	private IPerfilService perfilService;

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/obtenerRoles")
	public RespuestaObjetosJson<T> obtenerRoles() {

		List<PerfilResponseDto> perfilesDto = perfilService.obtenerRoles();

		if (perfilesDto != null) {
			return (new RespuestaObjetosJson<T>(new RespuestaDto(true, "", ""), (List<T>) perfilesDto));
		}
		return (new RespuestaObjetosJson<T>(
				new RespuestaDto(false, "NO existen Perfiles o Roles en la Base de Datos !!!", ""), null));

	}

}
