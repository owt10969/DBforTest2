package com.example.dbfortest.ui.personinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dbfortest.R;
import com.example.dbfortest.Registered;

public class PersoninfoFragment extends Fragment {
    private Registered.PersoninfoViewModel PersonInfoFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonInfoFragment =
                ViewModelProviders.of(this).get(Registered.PersoninfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personinfo, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        PersonInfoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
