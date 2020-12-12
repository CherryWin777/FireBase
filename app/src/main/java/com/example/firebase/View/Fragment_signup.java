package com.example.firebase.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.firebase.R;
import com.example.firebase.ViewModel.sign_upViewModel;
import com.google.firebase.auth.FirebaseUser;

public class Fragment_signup extends Fragment {
    private EditText email2,password2,edt_name;
    private Button btn2;
    private sign_upViewModel sign_upViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sign_upViewModel=new ViewModelProvider(this).get(sign_upViewModel.class);
        sign_upViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null){
                    Navigation.findNavController(getView()).navigate(R.id.action_fragment_signup_to_fragment_login);
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.signup,container,false);
        email2=view.findViewById(R.id.six);
        password2=view.findViewById(R.id.seven);
        btn2=view.findViewById(R.id.button2);
        edt_name= view.findViewById(R.id.name);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=email2.getText().toString();
                String password =password2.getText().toString();
                String userName=edt_name.getText().toString();

                if(email.length()>0 && password.length()>0){
                    sign_upViewModel.sign_up(email,password,userName);
                }
            }
        });
        return view;
    }
}
