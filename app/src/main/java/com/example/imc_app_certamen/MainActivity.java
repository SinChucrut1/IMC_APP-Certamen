package com.example.imc_app_certamen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Instanciar variables
    EditText peso, estatura;
    Button btnCalcular;
    Spinner spinnerSexo;

    TextView txtIMC, txtIndicaciones;
    
    ArrayAdapter<String> adapterSp;
    
    int posicionSelec = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definir variables por ID
        peso = findViewById(R.id.txtPeso);
        estatura = findViewById(R.id.txtEstatura);
        txtIMC = findViewById(R.id.txtIMC);
        txtIndicaciones = findViewById(R.id.txtIndicaciones);
        btnCalcular = findViewById(R.id.btnCalcular);
        spinnerSexo = findViewById(R.id.SpinnerSexo);
        
        // Definir array de opciones para spinner
        // Ademas de su arrayAdapter
        // Y su modo de seleccion
        String[] sexos = {"Seleccione", "Masculino", "Femenino"};
        adapterSp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sexos);
        spinnerSexo.setAdapter(adapterSp);
    }

    // Funcion para calcular IMC
    public void CalcularIMC(View view){
        // Obtener datos
        String Sexo = spinnerSexo.getSelectedItem().toString();
        String Peso = peso.getText().toString();
        String Estatura = estatura.getText().toString();

        // validacion de campos vacios
        if(!Sexo.equalsIgnoreCase("Seleccione") && !Peso.isEmpty() && !Estatura.isEmpty()){

            // parsear datos numericos a Double
            Double peso1 = Double.parseDouble(peso.getText().toString());
            // aqui de cm lo pasamos a metros
            Double estatura1 = Double.parseDouble(estatura.getText().toString()) / 100.0;

            // Formula para IMC
            // Almacenamos el resultado en una variable
            // peso / estatura(m)^2
            Double resultado = peso1 / (estatura1*estatura1);
            // Luego le acortamos decimales para que quede en formato "12,3"
            String calculo = Double.toString(resultado).substring(0,4);
            // Experimento: volverlo double de nuevo (Nmas pa ver)
            Double IMC = Double.valueOf(calculo);

            // Notificacion
            Toast.makeText(this, "Calculo realizado correctamente", Toast.LENGTH_SHORT).show();

            // Colocar texto en el campo
            txtIMC.setText("");
            txtIMC.setText("Su IMC es de: " + IMC);

        } else {
            Toast.makeText(this, "No se admiten campos vacios", Toast.LENGTH_SHORT).show();
        }

    }
}