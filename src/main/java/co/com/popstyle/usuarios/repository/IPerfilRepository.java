package co.com.popstyle.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.popstyle.usuarios.entity.PerfilEntity;

@Repository
public interface IPerfilRepository extends JpaRepository<PerfilEntity, Long> {
	
}
