package co.com.popstyle.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.popstyle.usuarios.entity.UsuarioEntity;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	
	public UsuarioEntity findByNombres(String nombre);
	
	@Query("select a from UsuarioEntity a where a.idUsuario = ?1")
	public UsuarioEntity findByIdUsuario(Long idUsuario);

	@Query("select a from UsuarioEntity a where a.email = ?1")
	public UsuarioEntity findByEmail(String email);

	public UsuarioEntity findByIdUsuarioOrEmail(Long codigo, String email);
	
}
