package com.example.pantallas_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroNota extends AppCompatActivity {

    private EditText editTxtNombreAlumno;
    private EditText editTxtSelecionarAsignatura;
    private EditText editTxtNotaExamen;
    private EditText editTxtNotaActividades;
    private EditText editTxtNotaFinal;
    private Button btnSelecionarAlumno;
    private Button btnSelecionarAsignatura;
    private Button btnCalcular;
    private Button buttonGuardar;
    private Button buttonLimpiar;

    private static final int REQUEST_ALUMNO = 1;
    private static final int REQUEST_ASIGNATURA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTxtNombreAlumno = findViewById(R.id.editTxtNombreAlumno);
        editTxtSelecionarAsignatura = findViewById(R.id.editTxtSelecionarAsignatura);
        editTxtNotaExamen = findViewById(R.id.editTxtNotaExamen);
        editTxtNotaActividades = findViewById(R.id.editTxtNotaActividades);
        editTxtNotaFinal = findViewById(R.id.editTxtNotaFinal);
        btnSelecionarAlumno = findViewById(R.id.btnSelecionarAlumno);
        btnSelecionarAsignatura = findViewById(R.id.btnSelecionarAsignatura);
        btnCalcular = findViewById(R.id.btnCalcular);
        buttonGuardar = findViewById(R.id.btnGuardar);
        buttonLimpiar = findViewById(R.id.btnLimpiar);

        btnSelecionarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroNota.this, SelecionAlumnos.class);
                startActivityForResult(intent, REQUEST_ALUMNO);
            }
        });

        btnSelecionarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroNota.this, SelecionAsignatura.class);
                startActivityForResult(intent, REQUEST_ASIGNATURA);
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularNota();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

        buttonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarDatos();
            }
        });




    }

    private void guardarDatos() {
        if (editTxtNombreAlumno.getText().toString().isEmpty() || editTxtSelecionarAsignatura.getText().toString().isEmpty() ||
                editTxtNotaExamen.getText().toString().isEmpty() || editTxtNotaActividades.getText().toString().isEmpty()) {
            Toast.makeText(this, "Los datos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simulación de guardado de datos
        Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        limpiarDatos();
    }

    private void limpiarDatos() {
        editTxtNombreAlumno.setText("");
        editTxtSelecionarAsignatura.setText("");
        editTxtNotaExamen.setText("");
        editTxtNotaActividades.setText("");
        editTxtNotaFinal.setText("");
        btnSelecionarAlumno.setEnabled(true);
        btnSelecionarAsignatura.setEnabled(true);
        btnCalcular.setEnabled(true);
    }

    private void calcularNota() {

        String notaExamenStr = editTxtNotaExamen.getText().toString();
        String notaActividadesStr = editTxtNotaActividades.getText().toString();

        if (notaExamenStr.isEmpty() || notaActividadesStr.isEmpty()) {
            Toast.makeText(this, "Los datos son necesarios", Toast.LENGTH_SHORT).show();
            return;
        }

        double notaExamen = Double.parseDouble(notaExamenStr);
        double notaActividades = Double.parseDouble(notaActividadesStr);
        double notaFinal;

        if (notaExamen >= 4.5 && notaActividades >= 7.5) {
            notaFinal = notaExamen * 0.7 + notaActividades * 0.3;
        } else if (notaExamen < 4.5) {
            notaFinal = notaExamen;
        } else {
            notaFinal = notaExamen * 0.7 + notaActividades * 0.3;
        }

        editTxtNotaFinal.setText(String.valueOf(notaFinal));
        btnCalcular.setEnabled(false);
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_ALUMNO) {
                String alumno = data.getStringExtra("alumno");
                editTxtNombreAlumno.setText(alumno);
                btnSelecionarAlumno.setEnabled(false);
            } else if (requestCode == REQUEST_ASIGNATURA) {
                String asignatura = data.getStringExtra("asignatura");
                editTxtSelecionarAsignatura.setText(asignatura);
                btnSelecionarAsignatura.setEnabled(false);
            }
        }
    }


}