package co.com.popstyle.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.popstyle.usuarios.entity.UsuarioPerfilEntity;
import co.com.popstyle.usuarios.entity.UsuarioPerfilId;

@Repository
public interface IUsuarioPerfilRepository extends JpaRepository<UsuarioPerfilEntity, UsuarioPerfilId> {

}
