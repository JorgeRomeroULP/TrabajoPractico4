package com.ulpdroid.trabajopractico4;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulpdroid.trabajopractico4.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private LoginActivityViewModel lvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        lvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);

        setContentView(binding.getRoot());

        solicitarPermisos();
        registrarBroadcast();

        lvm.getUserOk().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                pasarAlMenu();
            }
        });
        lvm.getUserNotOk().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mostrarMensaje("Usuario o contraseña incorrectos");
            }
        });
        lvm.getUserVacio().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mostrarMensaje("El usuario o la contraseña no pueden estar vacios");
            }
        });

        binding.btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarDatos();
            }
        });
    }

    private void registrarBroadcast() {
        LlamarReceiver llamar = new LlamarReceiver();
        registerReceiver(llamar, new IntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE"));
    }

    private void solicitarPermisos() {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
        }
    }

    private void pasarAlMenu() {
        Intent intent = new Intent(this, ActivityMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void verificarDatos() {
        lvm.setEmail(binding.etEmail.getText().toString());
        lvm.setPassword(binding.etPassword.getText().toString());
        lvm.verificarUserAndPass();
    }

    private void mostrarMensaje(String mensaje) {
        binding.tvMensaje.setText(mensaje);
        binding.tvMensaje.setVisibility(View.VISIBLE);
    }
}
