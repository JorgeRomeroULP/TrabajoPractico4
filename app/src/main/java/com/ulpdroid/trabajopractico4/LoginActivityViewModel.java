package com.ulpdroid.trabajopractico4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginActivityViewModel extends AndroidViewModel {

    private String email;
    private String password;

    private MutableLiveData<Boolean> userOk;
    private MutableLiveData<Boolean> userVacio;
    private MutableLiveData<Boolean> userNotOk;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MutableLiveData<Boolean> getUserOk() {
        if (userOk == null) userOk = new MutableLiveData<>();
        return userOk;
    }

    public MutableLiveData<Boolean> getUserVacio() {
        if (userVacio == null) userVacio = new MutableLiveData<>();
        return userVacio;
    }

    public MutableLiveData<Boolean> getUserNotOk() {
        if (userNotOk == null) userNotOk = new MutableLiveData<>();
        return userNotOk;
    }

    public void verificarUserAndPass() {
        if (email.equals("jorge") && password.equals("1234")) {
            userOk.setValue(true);
            return;
        }
        if (email.isEmpty() || password.isEmpty()) {
            userVacio.setValue(true);
            return;
        }
        userNotOk.setValue(true);
    }
}
