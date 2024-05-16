package apifestivo.apifestivo.core.interfaces.servicios;

import apifestivo.apifestivo.core.entidades.Festivo;

public interface IFestivosServicio {

    public Boolean verificar(int dia, int mes, int año);
    
}
