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

            // mostrar indicaciones
            MostrarIndicaciones(IMC);

        } else {
            Toast.makeText(this, "No se admiten campos vacios", Toast.LENGTH_SHORT).show();
        }

    }

    // Funcion para mostrar indicaciones segun IMC
    // Recibira de parametro un valor double
    public void MostrarIndicaciones(Double IMC){
        // Si IMC < 18.5, es bajo peso
        // Si IMC >= 18.5 y <= 24.9, es adecuado
        // Si IMC >= 25.0 y <= 29.9, sobrepeso
        // Si IMC >= 30.0 y <= 34.9, obesidad

        if (IMC < 18.5){
            String mensaje =
                    "Clasificacion: BAJO PESO\n" +
                    "1) Come con mas frecuencia. Empieza poco a poco a hacer 5 o 6 comidas mas pequeñas durante el dia\n" +
                    "2) Elige alimentos con muchos nutrientes\n" +
                    "3) Agrega aderezos\n" +
                    "4) Prueba licuados y batidos de frutas\n" +
                    "5) Controla que bebes y cuando\n" +
                    "6) Haz ejercicio";
            txtIndicaciones.setText("");
            txtIndicaciones.setText(mensaje);
        } else if(IMC >= 18.5 && IMC <= 24.9){
            String mensaje = "Felicidades! Usted mantiene una excelente alimentacion";
            txtIndicaciones.setText("");
            txtIndicaciones.setText(mensaje);

        } else if (IMC >= 25.0 && IMC <= 29.9){
            String mensaje =
                    "Clasificacion: SOBREPESO\n" +
                            "1) Haga 30 minutos de ejercicio aerobico 5 veces por semana\n" +
                            "2) Logre un equilibrio entre el consumo de calorias y la actividad fisica\n" +
                            "3) Limite las grasas saturadas a no mas de 7% de las calorias totales\n" +
                            "4) Tenga una alimentacion baja en colesterol con carnes magras, frutas, verduras y cereales integrales";
            txtIndicaciones.setText("");
            txtIndicaciones.setText(mensaje);

        } else if (IMC >= 30.0 && IMC <= 34.9){
            String mensaje =
                    "Clasificacion: OBESIDAD\n" +
                            "1) Haga 30 minutos de ejercicio aerobico 5 veces por semana\n" +
                            "2) Logre un equilibrio entre el consumo de calorias y la actividad fisica\n" +
                            "3) Limite las grasas saturadas a no mas de 7% de las calorias totales\n" +
                            "4) Tenga una alimentacion baja en colesterol con carnes magras, frutas, verduras y cereales integrales";
            txtIndicaciones.setText("");
            txtIndicaciones.setText(mensaje);
        } else {
            String mensaje = "Se sale de las metricas. PROXIMAMENTE...";
            txtIndicaciones.setText("");
            txtIndicaciones.setText(mensaje);
        }
    }

    // Link Publico GitHub
    // https://github.com/SinChucrut1/IMC_APP-Certamen.git

}