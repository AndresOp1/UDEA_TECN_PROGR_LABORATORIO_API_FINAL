package apifestivo.apifestivo.core.interfaces.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apifestivo.apifestivo.core.entidades.Festivos;

@Repository
public interface IFestivosRepositorio extends JpaRepository<Festivos, Integer>{
    
}
