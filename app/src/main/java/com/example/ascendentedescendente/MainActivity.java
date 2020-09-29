package com.example.ascendentedescendente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText entrada;
    private TextView tvAscendente;
    private TextView tvDescendente;
    private TextView tvMezclado;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada = findViewById(R.id.etInput);
        tvAscendente = findViewById(R.id.tvAsc);
        tvDescendente = findViewById(R.id.tvDesc);
        tvMezclado = findViewById(R.id.tvMezclado);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // al pulsar el boton se crean 2 hilos que van poniendo los numeros en los tv

                tvAscendente.setText("");
                tvDescendente.setText("");
                tvMezclado.setText("");

                // recogemos el numero del editText
                int numero = Integer.parseInt(entrada.getText().toString());

                // ASCENDENTE
                HiloAscendente hiloAscendente = new HiloAscendente(numero);

                // DESCENDENTE
                HiloDescendente hiloDescendente = new HiloDescendente(numero);


                hiloAscendente.start();
                hiloDescendente.start();


            }
        });



    }

    public void ascender (int n)
    {
        Random rnd = new Random ();

        for(int i = 1; i <= n; i++)
        {
            int numero = ((int)(rnd.nextDouble()*100000.0))%10000 + 10;
            System.out.println("Ascendente: " + numero);
            tvAscendente.append(i + " ");
            tvMezclado.append(i + " ");
        }

    }

    public void descender (int n)
    {
        Random rnd = new Random ();

        for(int i = n; i >= 1; i--)
        {

            int numero = ((int)(rnd.nextDouble()*100000.0))%1000 + 10;
            System.out.println("Descendente: " + numero);
            SystemClock.sleep(numero);

            tvDescendente.append(i + " ");
            tvMezclado.append(i + " ");
        }


    }

    public class HiloAscendente extends Thread {
        private int n;

        public HiloAscendente(int n) {
            this.n = n;
        }

        @Override
        public void run() {


            //necesario para poder usar los elementos visuales (view) y modificarlos
            runOnUiThread(new Runnable() {
                @Override
                public void run(){
                    ascender(n);
                }
            });
        }
    }

    public class HiloDescendente extends Thread {
        private int n;

        public HiloDescendente(int n) {
            this.n = n;
        }

        @Override
        public void run() {


            //necesario para poder usar los elementos visuales (view) y modificarlos
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    descender(n);
                }
            });
        }
    }
}