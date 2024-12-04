package datos;

public class NotasAlumnoAsig {
    private String nombreAlumno;
    private String asignatura;
    private double notaExamen;
    private double notaActividades;
    private double notaFinal;

    public NotasAlumnoAsig(String nombreAlumno, String asignatura, double notaExamen, double notaActividades, double notaFinal) {
        this.nombreAlumno = nombreAlumno;
        this.asignatura = asignatura;
        this.notaExamen = notaExamen;
        this.notaActividades = notaActividades;
        this.notaFinal = notaFinal;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    public double getNotaActividades() {
        return notaActividades;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaExamen(double notaExamen) {
        this.notaExamen = notaExamen;
    }

    public void setNotaActividades(double notaActividades) {
        this.notaActividades = notaActividades;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return "NotasAlumnoAsig{" +
                "nombreAlumno='" + nombreAlumno + '\'' +
                ", asignatura='" + asignatura + '\'' +
                ", notaExamen=" + notaExamen +
                ", notaActividades=" + notaActividades +
                ", notaFinal=" + notaFinal +
                '}';
    }
}