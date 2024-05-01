package apifestivo.apifestivo.core.interfaces.servicios;

import apifestivo.apifestivo.core.entidades.Festivos;

public interface IFestivosServicio {
    
    public Festivos verificar(int dia, int mes, int año);

}
