package org.source.utils;

import org.source.modelos.Clase;
import org.source.modelos.Curso;
import org.source.modelos.Estudiante;
import org.source.modelos.Profesor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta es la descripción de la clase FuncionalidadServicios:
 * @author Gabriel Paiva
 */

public class FuncionalidadServicios {

    public static String[] claseMasCercana (String codigo, Clave clave) throws IOException {

        String[] dataMasCercano = null;

        String HoraActual = obtenerHoraActual();

        Curso cursoMasCercano = new Curso();
        Clase claseMasCercana = new Clase();

        int[] comparadorCursos = new int[4];

        boolean esHoraCLase = false;

        for (int i = 0; i < comparadorCursos.length; i++) {
            comparadorCursos[i] = 10081; // Máximos minutos en una semana + 1
        }

        if (clave == Clave.PROFESOR){

            Profesor profesor = Constantes.dataProfesor().get(codigo);
            Curso[] cursos = profesor.getCursos();

            for (int i = 0; i < cursos.length; i++) {

                Clase[] clases = cursos[i].getClases();

                for (int j = 0; j < clases.length; j++) {
                    String HoraInicio = clases[j].getHoraInicio();
                    String HoraFinal = clases[j].getHoraFinal();
                    String DiaSemanaClase = clases[j].getDiaSemana();

                    esHoraCLase = esHoraDeClase(HoraInicio,HoraFinal);

                    if (esHoraCLase){
                        claseMasCercana = clases[j];
                        cursoMasCercano = cursos[i];
                        break;
                    }

                    comparadorCursos[0] = distanciaDiaDeClase(DiaSemanaClase);
                    comparadorCursos[1] = calcularMinutosHastaLlegada(HoraActual,HoraInicio,comparadorCursos[0]);

                    if (comparadorCursos[1] <= comparadorCursos[3]){

                        comparadorCursos[2] = comparadorCursos[0];
                        comparadorCursos[3] = comparadorCursos[1];

                        claseMasCercana = clases[j];
                        cursoMasCercano = cursos[i];
                    }
                }
                if (esHoraCLase){
                    break;
                }
            }

            dataMasCercano = new String[9];

            dataMasCercano[0] = profesor.getNombre();
            dataMasCercano[1] = cursoMasCercano.getNombreCurso();
            dataMasCercano[2] = claseMasCercana.getAmbiente().getNombreSede();

            dataMasCercano[4] = claseMasCercana.getAmbiente().getTorreOrAV();
            if (!dataMasCercano[4].equals("AV")){
                dataMasCercano[3] = claseMasCercana.getAmbiente().getNombreSede().substring(1);
            }else {
                dataMasCercano[3] = dataMasCercano[4];
            }
            dataMasCercano[5] = claseMasCercana.getAmbiente().getAmbienteDeClase();
            dataMasCercano[6] = claseMasCercana.getHorarioClase();
            dataMasCercano[7] = dataMasCercano[3];
            dataMasCercano[8] = claseMasCercana.getDiaSemana();

            return dataMasCercano;

        }

        if (clave == Clave.ALUMNO){
            Estudiante estudiante = Constantes.dataEstudiante().get(codigo);

            Curso[] cursos = estudiante.getCursos();

            for (int i = 0; i < cursos.length; i++) {

                Clase[] clases = cursos[i].getClases();

                for (int j = 0; j < clases.length; j++) {
                    String HoraInicio = clases[j].getHoraInicio();
                    String HoraFinal = clases[j].getHoraFinal();
                    String DiaSemanaClase = clases[j].getDiaSemana();

                    if (DiaSemanaClase.equals(obtenerDiaSemanaActual())){
                        esHoraCLase = esHoraDeClase(HoraInicio,HoraFinal);
                    }

                    if (esHoraCLase){
                        claseMasCercana = clases[j];
                        cursoMasCercano = cursos[i];
                        break;
                    }

                    comparadorCursos[0] = distanciaDiaDeClase(DiaSemanaClase);
                    comparadorCursos[1] = calcularMinutosHastaLlegada(HoraActual,HoraInicio,comparadorCursos[0]);

                    if (comparadorCursos[1] <= comparadorCursos[3]){

                        comparadorCursos[2] = comparadorCursos[0];
                        comparadorCursos[3] = comparadorCursos[1];

                        claseMasCercana = clases[j];
                        cursoMasCercano = cursos[i];
                    }
                }
                if (esHoraCLase){
                    break;
                }
            }

            dataMasCercano = new String[9];

            dataMasCercano[0] = estudiante.getNombre();
            dataMasCercano[1] = cursoMasCercano.getNombreCurso();
            dataMasCercano[2] = claseMasCercana.getAmbiente().getNombreSede();

            dataMasCercano[4] = claseMasCercana.getAmbiente().getTorreOrAV();
            if (!dataMasCercano[4].equals("AV")){
                dataMasCercano[3] = claseMasCercana.getAmbiente().getNombreSede().substring(1);
            }else {
                dataMasCercano[3] = dataMasCercano[4];
            }
            dataMasCercano[5] = claseMasCercana.getAmbiente().getAmbienteDeClase();
            dataMasCercano[6] = claseMasCercana.getHorarioClase();
            dataMasCercano[7] = dataMasCercano[3];
            dataMasCercano[8] = claseMasCercana.getDiaSemana();

            return dataMasCercano;
        }
        return dataMasCercano;
    }

    public static boolean esHoraDeClase(String horaInicio, String horaFin) {
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime inicioClase = LocalTime.parse(horaInicio, formatter);
        LocalTime finClase = LocalTime.parse(horaFin, formatter);
        return horaActual.isAfter(inicioClase) && horaActual.isBefore(finClase);
    }

    public static DayOfWeek convertirDia(String diaClase) {
        Map<String, String> dias = new HashMap<>();
        dias.put("Lunes", "MONDAY");
        dias.put("Martes", "TUESDAY");
        dias.put("Miércoles", "WEDNESDAY");
        dias.put("Jueves", "THURSDAY");
        dias.put("Viernes", "FRIDAY");
        dias.put("Sábado", "SATURDAY");
        dias.put("Domingo", "SUNDAY");

        return DayOfWeek.valueOf(dias.get(diaClase));
    }
    public static int distanciaDiaDeClase(String diaClase) {
        DayOfWeek dia = convertirDia(diaClase);
        DayOfWeek hoy = LocalDate.now().getDayOfWeek();

        if (dia.compareTo(hoy) >= 0) {
            return dia.getValue() - hoy.getValue();
        } else {
            return 7 - (hoy.getValue() - dia.getValue());
        }
    }
    public static String obtenerHoraActual() {
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        String horaActualFormateada = horaActual.format(formatoHora);
        return horaActualFormateada;
    }

    public static int calcularMinutosHastaLlegada(String horaActual, String horaLlegada, int DiasDif) {
        int minutosHastaLlegada = 0;

        if (DiasDif == 0) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date date1 = format.parse(horaActual);
                Date date2 = format.parse(horaLlegada);
                long diferencia = date2.getTime() - date1.getTime();

                if (diferencia < 0) {
                    format = new SimpleDateFormat("HH:mm");
                    date1 = format.parse(horaActual);
                    date2 = format.parse("24:00");
                    diferencia = date2.getTime() - date1.getTime();

                    Date date3 = format.parse("00:00");
                    Date date4 = format.parse(horaLlegada);
                    long diferencia2 = date4.getTime() - date3.getTime();

                    minutosHastaLlegada = (int) (diferencia / (1000 * 60) + diferencia2 / (1000 * 60)) + (5*1440);
                }else{
                    minutosHastaLlegada = (int) (diferencia / (1000 * 60));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return minutosHastaLlegada;
        }

        if (DiasDif == 1){
            try {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date date1 = format.parse(horaActual);
                Date date2 = format.parse("24:00");
                long diferencia = date2.getTime() - date1.getTime();

                Date date3 = format.parse("00:00");
                Date date4 = format.parse(horaLlegada);
                long diferencia2 = date4.getTime() - date3.getTime();

                minutosHastaLlegada = (int) (diferencia / (1000 * 60) + diferencia2 / (1000 * 60));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return minutosHastaLlegada;
        }

        if (DiasDif > 1){
            try {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date date1 = format.parse(horaActual);
                Date date2 = format.parse("24:00");
                long diferencia = date2.getTime() - date1.getTime();

                Date date3 = format.parse("00:00");
                Date date4 = format.parse(horaLlegada);
                long diferencia2 = date4.getTime() - date3.getTime();

                minutosHastaLlegada = (int) (diferencia / (1000 * 60) + diferencia2 / (1000 * 60)) + ((DiasDif-1)*1440);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return minutosHastaLlegada;
        }
        return minutosHastaLlegada;
    }

    public static String obtenerDiaSemanaActual() {
        Calendar calendario = Calendar.getInstance();
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

        switch(diaSemana) {
            case Calendar.MONDAY:
                return "Lunes";
            case Calendar.TUESDAY:
                return "Martes";
            case Calendar.WEDNESDAY:
                return "Miércoles";
            case Calendar.THURSDAY:
                return "Jueves";
            case Calendar.FRIDAY:
                return "Viernes";
            case Calendar.SATURDAY:
                return "Sábado";
            case Calendar.SUNDAY:
                return "Domingo";
            default:
                return "";
        }
    }
}
