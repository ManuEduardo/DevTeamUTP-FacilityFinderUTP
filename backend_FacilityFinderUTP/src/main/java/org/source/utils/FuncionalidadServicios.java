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
import java.util.*;

/**
 * Esta es la descripción de la clase FuncionalidadServicios:
 * Esta es una clase llamada "FuncionalidadServicios" que contiene un método estático llamado
 * "claseMasCercana". El método toma un código y una clave como parámetros, y devuelve un array de
 * strings con información sobre la clase más cercana según la clave proporcionada.
 *
 * El método busca la clase más cercana para un profesor o un estudiante en particular. Para los
 * profesores, busca la próxima clase que tengan, y para los estudiantes, busca la próxima clase
 * en el mismo día que el estudiante está consultando.
 *
 * Para hacer esto, el método realiza iteraciones a través de la lista de cursos y clases asociadas
 * para el profesor o estudiante correspondiente. Para cada clase, calcula la distancia en días
 * a la clase y la cantidad de minutos hasta que comience la clase. Luego, compara esta información
 * con la información recopilada previamente para determinar si esta clase es la más cercana.
 * Finalmente, devuelve la información sobre la clase más cercana encontrada en el formato de un
 * array de strings con información relevante sobre la clase.
 *
 * @author Gabriel Paiva
 * @version 1.0
 * @since 2023-03-19
 */

public class FuncionalidadServicios {
    /**
     * La función "claseMasCercana" tiene como objetivo encontrar la clase más cercana a la hora
     * actual para un profesor o estudiante, dependiendo del parámetro "clave". Para ello, utiliza
     * un algoritmo que busca entre los cursos del profesor o estudiante y sus clases asociadas,
     * y determina la clase más cercana en tiempo al momento actual.
     *
     * La función hace uso de otras funciones ya definidas, como "obtenerHoraActual", "esHoraDeClase",
     * "distanciaDiaDeClase" y "calcularMinutosHastaLlegada", que se encargan de obtener la hora actual
     * , verificar si una hora dada corresponde a una clase, determinar la distancia entre el día
     * actual y el día de una clase, y calcular los minutos faltantes para llegar a una hora determinada,
     * respectivamente.
     *
     * En resumen, "claseMasCercana" es una función que utiliza un algoritmo para determinar la clase
     * más cercana en tiempo al momento actual para un profesor o estudiante, y devuelve información
     * relevante de dicha clase en un arreglo de strings.
     *
     * @param codigo Toma como entrada el código del profesor o estudiante como String.
     * @param clave Enum que representa la clave que indica si se trata de un profesor o estudiante.
     * @return Devuelve un arreglo de strings con información relevante de la clase más cercana
     * encontrada, como el nombre del profesor o estudiante, el nombre del curso, la sede, la torre
     * o avenida, el ambiente, el horario de la clase, el día de la semana y la ubicación.
     */
    public static String[] claseMasCercana (String codigo, Clave clave) throws IOException {

        // Inicializa los objetos a utilizar.
        String[] dataMasCercano = null;
        Curso cursoMasCercano = new Curso();
        Clase claseMasCercana = new Clase();
        int[] comparadorCursos = new int[4];
        boolean esHoraCLase = false;

        // Obtiene la hora actual en forma de String.
        String HoraActual = obtenerHoraActual();

        // Máximos minutos en una semana + 1
        Arrays.fill(comparadorCursos, 10081);

        // Condicional en caso el código que ingrese sea de un profesor.
        if (clave == Clave.PROFESOR){

            // Obtiene la information del profesor con base en su código de profesor.
            Profesor profesor = Constantes.dataProfesor().get(codigo);
            Curso[] cursos = profesor.getCursos();

            // Recorre los cursos y las clases del profesor.
            for (Curso curso : cursos) {

                Clase[] clases = curso.getClases();

                for (Clase clase : clases) {
                    String HoraInicio = clase.getHoraInicio();
                    String HoraFinal = clase.getHoraFinal();
                    String DiaSemanaClase = clase.getDiaSemana();

                    if (DiaSemanaClase.equals(obtenerDiaSemanaActual())) {
                        esHoraCLase = esHoraDeClase(HoraInicio, HoraFinal);
                    }

                    // En clase actualmente sea la hora de clase sale del bucle, porque la clase estaría
                    // en curso.
                    if (esHoraCLase) {
                        claseMasCercana = clase;
                        cursoMasCercano = curso;
                        break;
                    }

                    // Comparador de cursos se estructura de la siguiente manera
                    // [0] = Distancia entre dias.
                    // [1] = Distancia entre minutos.
                    // [2] = Distancia entre dias minima.
                    // [3] = Distancia entre minutos minima.

                    comparadorCursos[0] = distanciaDiaDeClase(DiaSemanaClase);
                    comparadorCursos[1] = calcularMinutosHastaLlegada(HoraActual, HoraInicio, comparadorCursos[0]);

                    // Comparador para obtener la clase que está más cercana a suceder según
                    // los minutos entre la clase y la hora actual.
                    if (comparadorCursos[1] <= comparadorCursos[3]) {

                        comparadorCursos[2] = comparadorCursos[0];
                        comparadorCursos[3] = comparadorCursos[1];

                        claseMasCercana = clase;
                        cursoMasCercano = curso;
                    }
                }
                if (esHoraCLase) {
                    break;
                }
            }

            // Obtención de la data.
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

        // Condicional en caso el código que ingrese sea de un Alumno.
        if (clave == Clave.ALUMNO){
            Estudiante estudiante = Constantes.dataEstudiante().get(codigo);

            Curso[] cursos = estudiante.getCursos();

            // Recorre los cursos y las clases del estudiante.
            for (Curso curso : cursos) {

                Clase[] clases = curso.getClases();


                for (Clase clase : clases) {
                    String HoraInicio = clase.getHoraInicio();
                    String HoraFinal = clase.getHoraFinal();
                    String DiaSemanaClase = clase.getDiaSemana();

                    if (DiaSemanaClase.equals(obtenerDiaSemanaActual())) {
                        esHoraCLase = esHoraDeClase(HoraInicio, HoraFinal);
                    }

                    // En clase actualmente sea la hora de clase sale del bucle, porque la clase estaría
                    // en curso.
                    if (esHoraCLase) {
                        claseMasCercana = clase;
                        cursoMasCercano = curso;
                        break;
                    }

                    // Comparador de cursos se estructura de la siguiente manera
                    // [0] = Distancia entre dias.
                    // [1] = Distancia entre minutos.
                    // [2] = Distancia entre dias minima.
                    // [3] = Distancia entre minutos minima.

                    comparadorCursos[0] = distanciaDiaDeClase(DiaSemanaClase);
                    comparadorCursos[1] = calcularMinutosHastaLlegada(HoraActual, HoraInicio, comparadorCursos[0]);

                    if (comparadorCursos[1] <= comparadorCursos[3]) {

                        comparadorCursos[2] = comparadorCursos[0];
                        comparadorCursos[3] = comparadorCursos[1];

                        claseMasCercana = clase;
                        cursoMasCercano = curso;
                    }
                }
                if (esHoraCLase) {
                    break;
                }
            }

            // Obtención de la data.
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

        // Como no existe otro tipo de objeto ENUM devolverá null.
        return null;

    }

    /**
     Función que verifica si la hora actual está dentro del rango de horas de una clase.
     @param horaInicio Una cadena que representa la hora de inicio de la clase en formato "HH:mm".
     @param horaFin Una cadena que representa la hora de fin de la clase en formato "HH:mm".
     @return true si la hora actual está dentro del rango de horas de la clase, false en caso contrario.*/
    public static boolean esHoraDeClase(String horaInicio, String horaFin) {

        // La función utiliza la clase "LocalTime" de la librería "java.time" para obtener la hora
        // actual del sistema.
        LocalTime horaActual = LocalTime.now();

        // Luego, la función parsea las cadenas que representan las horas de inicio y fin de la clase,
        // utilizando un objeto "DateTimeFormatter" con formato "HH:mm".
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime inicioClase = LocalTime.parse(horaInicio, formatter);
        LocalTime finClase = LocalTime.parse(horaFin, formatter);

        // Por último, la función utiliza el método "isAfter" y "isBefore" de la clase "LocalTime"
        // para verificar si la hora actual está dentro del rango de horas de la clase.
        // La función retorna true si la hora actual está dentro del rango de horas de la clase,
        // false en caso contrario.
        return horaActual.isAfter(inicioClase) && horaActual.isBefore(finClase);

    }

    /**
     Función que convierte un día de la semana en formato String a un objeto DayOfWeek.
     @param diaClase El día de la semana en formato String.
     @return Un objeto DayOfWeek que representa el día de la semana. */
    public static DayOfWeek convertirDia(String diaClase) {

        // Mapa que asocia los nombres de los días en español con los nombres en inglés usados por DayOfWeek.
        Map<String, String> dias = new HashMap<>();
        dias.put("Lunes", "MONDAY");
        dias.put("Martes", "TUESDAY");
        dias.put("Miércoles", "WEDNESDAY");
        dias.put("Jueves", "THURSDAY");
        dias.put("Viernes", "FRIDAY");
        dias.put("Sábado", "SATURDAY");
        dias.put("Domingo", "SUNDAY");

        // Devuelve el objeto DayOfWeek correspondiente al día de la semana especificado.
        return DayOfWeek.valueOf(dias.get(diaClase));

    }

    /**
     Función que calcula la cantidad de días entre el día actual y un día de la semana específico.
     @param diaClase Una cadena que representa el día de la semana de la clase.
     @return Un entero que representa la cantidad de días entre hoy y el día de la semana especificado. */
    public static int distanciaDiaDeClase(String diaClase) {

        // Convierte la cadena del día de la clase a un objeto DayOfWeek.
        // Luego, obtiene el día de la semana actual a través del método getDayOfWeek() de LocalDate.now().
        DayOfWeek dia = convertirDia(diaClase);
        DayOfWeek hoy = LocalDate.now().getDayOfWeek();

        // Si el día de la clase es mayor o igual al día actual, se calcula la diferencia entre los
        // valores numéricos de los días.

        // Si el día de la clase es menor que el día actual, se resta el valor numérico del día de la
        // clase del valor numérico del día actual, y se resta el resultado de 7 para obtener la
        // cantidad de días restantes.

        // Devuelve la cantidad de días calculados.
        if (dia.compareTo(hoy) >= 0) {
            return dia.getValue() - hoy.getValue();
        } else {
            return 7 - (hoy.getValue() - dia.getValue());
        }

    }

    /**
     Función que obtiene la hora actual del sistema y la devuelve en formato de cadena "HH:mm".
     @return Una cadena que representa la hora actual en formato "HH:mm". */
    public static String obtenerHoraActual() {

        // Obtiene la hora actual del sistema.
        LocalTime horaActual = LocalTime.now();

        // Define el formato para la hora "HH:mm".
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        // Formatea la hora actual de acuerdo al formato definido y la devuelve.
        return horaActual.format(formatoHora);

    }

    /**
     Función que calcula los minutos que faltan para llegar a una hora determinada,
     considerando la hora actual y la diferencia en días entre hoy y el día de llegada.
     @param horaActual Una cadena que representa la hora actual en formato "HH:mm".
     @param horaClase Una cadena que representa la hora de llegada en formato "HH:mm".
     @param DiasDif Un entero que representa la diferencia en días entre hoy y el día de llegada.
     @return El número de minutos que faltan para llegar a la hora de llegada.*/
    public static int calcularMinutosHastaLlegada(String horaActual, String horaClase, int DiasDif) {
        int minutosHastaLlegada = 0;

        // Si hoy es el mismo día de la clase.
        if (DiasDif == 0) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date date1 = format.parse(horaActual);
                Date date2 = format.parse(horaClase);
                long diferencia = date2.getTime() - date1.getTime();

                // Si la hora de Clase es antes que la hora actual (ej. 23:00 para una clase que es a las 01:00):
                if (diferencia < 0) {
                    format = new SimpleDateFormat("HH:mm");
                    date1 = format.parse(horaActual);
                    date2 = format.parse("24:00");
                    diferencia = date2.getTime() - date1.getTime();

                    Date date3 = format.parse("00:00");
                    Date date4 = format.parse(horaClase);
                    long diferencia2 = date4.getTime() - date3.getTime();

                    // Minutos hasta la siguiente clase.
                    minutosHastaLlegada = (int) (diferencia / (1000 * 60) + diferencia2 / (1000 * 60)) + (5*1440);
                }else{
                    // Si la hora de Clase es después de la hora actual:
                    minutosHastaLlegada = (int) (diferencia / (1000 * 60));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return minutosHastaLlegada;
        }

        // Si mañana es el día de la clase.
        if (DiasDif == 1){
            try {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date date1 = format.parse(horaActual);
                Date date2 = format.parse("24:00");
                long diferencia = date2.getTime() - date1.getTime();

                Date date3 = format.parse("00:00");
                Date date4 = format.parse(horaClase);
                long diferencia2 = date4.getTime() - date3.getTime();

                minutosHastaLlegada = (int) (diferencia / (1000 * 60) + diferencia2 / (1000 * 60));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return minutosHastaLlegada;
        }

        // Si la clase ocurre en días posteriores.
        if (DiasDif > 1){
            try {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date date1 = format.parse(horaActual);
                Date date2 = format.parse("24:00");
                long diferencia = date2.getTime() - date1.getTime();

                Date date3 = format.parse("00:00");
                Date date4 = format.parse(horaClase);
                long diferencia2 = date4.getTime() - date3.getTime();

                minutosHastaLlegada = (int) (diferencia / (1000 * 60) + diferencia2 / (1000 * 60)) + ((DiasDif-1)*1440);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return minutosHastaLlegada;
        }
        return minutosHastaLlegada;

        /*
        Este método recibe tres parámetros:

        horaActual: una cadena que representa la hora actual en formato HH:mm
        horaLlegada: una cadena que representa la hora de llegada en formato HH:mm
        DiasDif: un número entero que representa la cantidad de días que faltan para la llegada
        El método retorna un entero que representa la cantidad de minutos que faltan para la llegada, teniendo en cuenta la hora actual y la hora de llegada, y también la cantidad de días que faltan.
        Si el valor de DiasDif es 0, entonces se calcula la cantidad de minutos hasta la llegada en el mismo día.
        Si el valor de DiasDif es 1, entonces se calcula la cantidad de minutos hasta la llegada en el día siguiente.
        Si el valor de DiasDif es mayor a 1, entonces se calcula la cantidad de minutos hasta la llegada sumando la cantidad de minutos que faltan para el final del día actual, y luego sumando la cantidad de minutos completos por cada día adicional hasta la llegada.

        En caso de que ocurra una excepción durante el cálculo, se imprime el stack trace en la consola.
        */

    }

    /**
     * Función que devuelve el día de la semana actual en forma de String.
     * @return El nombre del día de la semana actual en forma de String. */
    public static String obtenerDiaSemanaActual() {

        // Obtener una instancia del calendario actual
        Calendar calendario = Calendar.getInstance();

        // Obtener el día de la semana actual en formato entero (1 = Domingo, 2 = Lunes, ..., 7 = Sábado)
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

        // Utilizar una expresión switch para retornar el nombre del día de la semana actual en formato String
        return switch (diaSemana) {
            case Calendar.MONDAY -> "Lunes";
            case Calendar.TUESDAY -> "Martes";
            case Calendar.WEDNESDAY -> "Miércoles";
            case Calendar.THURSDAY -> "Jueves";
            case Calendar.FRIDAY -> "Viernes";
            case Calendar.SATURDAY -> "Sábado";
            case Calendar.SUNDAY -> "Domingo";

            // En caso de que no se reconozca el valor del día de la semana, retornar un String vacío
            default -> "";
        };
    }
}
