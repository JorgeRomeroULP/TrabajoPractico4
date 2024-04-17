package com.ulpdroid.trabajopractico4.ui.home;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class HomeViewModel extends AndroidViewModel {

    private String numero;

    private MutableLiveData<String> telefono;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public MutableLiveData<String> getTelefono() {
        if (telefono == null) {
            telefono = new MutableLiveData<>();
        }
        return telefono;
    }

    public void numeroOk() {
        for (int i = 0; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                Toast.makeText(getApplication(), "Debe ingresar solo digitos", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (numero.length() != 10) {
            Toast.makeText(getApplication(), "El numero debe tener 10 digitos", Toast.LENGTH_SHORT).show();
            return;
        }
        telefono.setValue(numero);
    }
}