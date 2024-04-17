package com.ulpdroid.trabajopractico4.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulpdroid.trabajopractico4.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel hvm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        hvm = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        hvm.getTelefono().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String telefono) {
                llamarTelefono(telefono);
            }
        });

        binding.btLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hvm.setNumero(binding.etLlamar.getText().toString());
                hvm.numeroOk();
            }
        });

        return root;
    }

    private void llamarTelefono(String telefono) {
        Intent intentLlamar = new Intent(Intent.ACTION_CALL);
        intentLlamar.setData(Uri.parse("tel:" + telefono));
        intentLlamar.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentLlamar);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}