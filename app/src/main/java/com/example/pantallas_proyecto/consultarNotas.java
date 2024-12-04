package com.example.pantallas_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import datos.ListadoNotas;
import datos.NotasAlumnoAsig;


public class ConsultarNotas extends AppCompatActivity {

    private EditText etAlumno;
    private Button btnSeleccionarAlumno;
    private LinearLayout contenedorNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_notas);

        etAlumno = findViewById(R.id.etAlumno);
        btnSeleccionarAlumno = findViewById(R.id.btnSeleccionarAlumno);
        contenedorNotas = findViewById(R.id.contenedorNotas);

        btnSeleccionarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSeleccionarAlumno.getText().toString().equals("Seleccionar Alumno")) {
                    Intent intent = new Intent(ConsultarNotas.this, SelecionAlumnos.class);
                    startActivityForResult(intent, 1);
                } else {
                    limpiarDatos();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nombreAlumno = data.getStringExtra("nombreAlumno");
            etAlumno.setText(nombreAlumno);
            btnSeleccionarAlumno.setText("Limpiar Datos");
            cargarNotasAlumno(nombreAlumno);
        }
    }

    public void cargarNotasAlumno(String nombreAlumno) {
        List<ListadoNotas> notas = ListadoNotas.obtenerNotasPorAlumno(nombreAlumno);
        contenedorNotas.removeAllViews();

        for (ListadoNotas nota : notas) {
            if (nota.getNotaFinal() != 0) {
                NotaFragment fragment = NotaFragment.newInstance(nota.getAsignatura(), nota.getNotaFinal());
                agregarFragment(fragment);
            }
        }
    }

    private void agregarFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(contenedorNotas.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void limpiarDatos() {
        etAlumno.setText("");
        btnSeleccionarAlumno.setText("Seleccionar Alumno");
        contenedorNotas.removeAllViews();
    }
}