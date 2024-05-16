package apifestivo.apifestivo.aplicacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apifestivo.apifestivo.core.entidades.Festivo;
import apifestivo.apifestivo.core.interfaces.repositorios.IFestivosRepositorio;
import apifestivo.apifestivo.core.interfaces.servicios.IFestivosServicio;
import apifestivo.apifestivo.utils.Fechas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;

@Service
public class FestivosServicio implements IFestivosServicio {

    @Autowired
    private IFestivosRepositorio festivosRepositorio;

    @Override
    public Boolean verificar(int dia, int mes, int año) {
        List<Festivo> festivos = calcularFestivos(año);
        festivos.stream().forEach(n-> System.out.println("dia "+n.getDia() + " mes" + n.getMes() + " tipo:" + n.getTipo().getId() + " año" + año));

        for (Festivo festivoBD : festivos) {
            if (festivoBD.getDia() == dia && festivoBD.getMes() == mes) {
                return true;
            }
        }
        return false;
        }    

    public static Date getDomingoRamos(int año) {

        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;

        // si el dia pasa mas de 31, entonces la fecha no da, usamos un condicional
        if (dia > 31) {
            dia = dia - 31;
            mes = 4;
        }
        // al año le resto 1900 ya que es la escala en que se trabaja
        // el mes es un dia de 0 a 11 entonces restamos 1
        return new Date(año - 1900, mes - 1, dia);
    }

    public static Date agregarDias(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DATE, dias);
        return calendar.getTime();

    }


public static Date siguienteLunes(Date fecha) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
    if (diaSemana != Calendar.MONDAY) {
        int diasHastaLunes = (Calendar.SATURDAY - diaSemana + 2) % 7;
        fecha = agregarDias(fecha, diasHastaLunes);
    }
    return fecha;
}

private List<Festivo> calcularFestivos(int año) {
    List<Festivo> festivosBD = festivosRepositorio.findAll();
    for (Festivo festivo : festivosBD) {
        if (festivo != null) {
        LocalDate pascua = getDomingoRamos(año).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(7);
            switch (festivo.getTipo().getId()) {
                case 2:
                    LocalDate fecha = LocalDate.of(año, festivo.getMes(), festivo.getDia());
                    if (fecha.getDayOfWeek() != DayOfWeek.MONDAY) {
                        fecha = fecha.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                    }
                    festivo.setDia(fecha.getDayOfMonth());
                    festivo.setMes(fecha.getMonthValue());
                    break;
                case 3:
                    LocalDate fecha1 = pascua.plusDays(festivo.getDiasPascua());
                    festivo.setDia(fecha1.getDayOfMonth());
                    festivo.setMes(fecha1.getMonthValue());
                    break;
                case 4:
                    LocalDate fecha2 = pascua.plusDays(festivo.getDiasPascua());
                    if (fecha2.getDayOfWeek() != DayOfWeek.MONDAY) {
                        fecha2 = fecha2.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                    }
                    festivo.setDia(fecha2.getDayOfMonth());
                    festivo.setMes(fecha2.getMonthValue());
                    break;
            }
        }
    }
    return festivosBD;
}
   
    
}