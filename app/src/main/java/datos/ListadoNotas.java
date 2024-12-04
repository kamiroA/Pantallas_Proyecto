package datos;

import java.util.ArrayList;
import java.util.List;

public class ListadoNotas {
    private List<NotasAlumnoAsig> listaNotas;

    public ListadoNotas() {
        listaNotas = new ArrayList<>();
        inicializarNotas();
    }

    private void inicializarNotas() {
        String[] alumnos = {
                "Víctor Anguita Martínez de Velasco", "Miguel Cañizares Chichon", "Nander Antonio Cueva Machuca",
                "Bony Pablo Diez Ateca", "Ana Coral Fernández Arteta", "Ignacio Fernández Periáñez",
                "Bernardino Font García", "Alvaro García Guimaraens", "Mario Gómez Pérez", "Carlos Hernández Borja",
                "Axel León Arroyo", "Camilo Armando Maita Bracamonte", "Oscar Martín García", "Guillermo Martín Muñoz",
                "Clara Nuevo Mota", "Alejandro Martínez Bravo", "Adrian Ignacio Ocaña de Frutos", "Lucas René Oliveira Urrutia",
                "Alejandro Ramírez Gómez", "Roberto Ruíz", "Angel Santana Aguilera", "Cindy Vanessa Taboada Guerra"
        };

        String[] asignaturas = {"PMDM", "AD", "PSP", "DI", "SGE", "IACC", "IOS"};

        for (String asignatura : asignaturas) {
            for (String alumno : alumnos) {
                listaNotas.add(new NotasAlumnoAsig(alumno, asignatura, 0, 0, 0));
            }
        }
    }

    public NotasAlumnoAsig buscarNota(String nombreAlumno, String asignatura) {
        for (NotasAlumnoAsig nota : listaNotas) {
            if (nota.getNombreAlumno().equals(nombreAlumno) && nota.getAsignatura().equals(asignatura)) {
                return nota;
            }
        }
        return null; // No se encontró
    }

    public void modificarNota(String nombreAlumno, String asignatura, double nuevaNotaExamen, double nuevaNotaActividades) {
        NotasAlumnoAsig nota = buscarNota(nombreAlumno, asignatura);
        if (nota != null) {
            nota.setNotaExamen(nuevaNotaExamen);
            nota.setNotaActividades(nuevaNotaActividades);
            nota.setNotaFinal((nuevaNotaExamen + nuevaNotaActividades) / 2); // Ejemplo de cálculo de nota final
        }
    }

    public static List<ListadoNotas> obtenerNotasPorAlumno(String nombreAlumno) {
        List<NotasAlumnoAsig> notasDelAlumno = new ArrayList<>();
        for (NotasAlumnoAsig nota : listaNotas) {
            if (nota.getNombreAlumno().equals(nombreAlumno) && nota.getNotaFinal() > 0) {
                notasDelAlumno.add(nota);
            }
        }
        return notasDelAlumno;
    }
}
