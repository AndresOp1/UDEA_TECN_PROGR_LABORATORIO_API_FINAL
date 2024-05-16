package apifestivo.apifestivo.utils;

import java.util.Calendar;
import java.util.Date;

public class Fechas {
    // aca calculo el numero de dias que pasan despues del 15 de marzo
    // para calcular el domingo de ramos
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
        if (calendar.get(calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
            fecha = agregarDias(fecha, 9 - calendar.get(calendar.DAY_OF_WEEK));
        } else {
            fecha = agregarDias(fecha, 1);
        }

        System.out.println(calendar.get(calendar.DAY_OF_WEEK));

        return fecha;
    }

}