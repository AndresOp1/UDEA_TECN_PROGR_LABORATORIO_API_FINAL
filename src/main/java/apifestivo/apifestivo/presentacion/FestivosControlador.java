package apifestivo.apifestivo.presentacion;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apifestivo.apifestivo.core.entidades.Festivo;
import apifestivo.apifestivo.core.interfaces.servicios.IFestivosServicio;

@RestController
@RequestMapping("api/festivos")
public class FestivosControlador {

    private IFestivosServicio servicio;

    public FestivosControlador(IFestivosServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/verificarFestivo/{dia}/{mes}/{año}", method = RequestMethod.GET)
    public String verificarFestivo(@PathVariable int dia, @PathVariable int mes, @PathVariable int año) {
        Boolean esFestivo = servicio.verificar(dia, mes, año);
        if (esFestivo) {
            return "Es festivo";
        } else {
            return "No es festivo";
        }
    }

    public  boolean validarFecha(String fechaString) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Esto hace que la validación sea estricta

            try {
                sdf.parse(fechaString);
                return true; // La fecha es válida
            } catch (ParseException e) {
                return false; // La fecha no es válida
            }
        }

   
}