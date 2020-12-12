package com.example.firebase.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.firebase.R;
import com.example.firebase.ViewModel.loginViewModel;
import com.google.firebase.auth.FirebaseUser;



public class Fragment_login extends Fragment{
    private EditText email1,password1,edt_name;
    private Button btn1;
    private loginViewModel loginViewModel;
    private TextView go_signUp;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel=new ViewModelProvider(this).get(loginViewModel.class);
        loginViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null && firebaseUser.isEmailVerified()){
                    Navigation.findNavController(getView()).navigate(R.id.action_fragment_login_to_fragment_home);
                    getActivity().getFragmentManager().popBackStack();
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login,container,false);
        email1=view.findViewById(R.id.two);
        password1=view.findViewById(R.id.three);
        btn1=view.findViewById(R.id.button1);
        go_signUp=view.findViewById(R.id.four);
        edt_name=view.findViewById(R.id.name);



        go_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_fragment_login_to_fragment_signup);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName=edt_name.getText().toString();
                String email=email1.getText().toString();
                String password=password1.getText().toString();
                if(email.length()>0 && password.length()>0){
                    loginViewModel.login(email,password,userName);
                }
            }
        });
        return view;
    }

}

