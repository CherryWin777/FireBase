package com.example.firebase.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.firebase.R;
import com.example.firebase.ViewModel.HomeViewModel;
import com.google.firebase.auth.FirebaseUser;

public class Fragment_home extends Fragment {
    private EditText email3;
    private Button btn3;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel=new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null){
                    email3.setText(firebaseUser.getEmail());
                    btn3.setEnabled(true);
                }else{
                    btn3.setEnabled(false);
                }
            }
        });
    homeViewModel.getLoggedOUtMutableLiveData().observe(this, new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isloggedOut) {
            if(isloggedOut){
                Toast.makeText(getContext(),"logged out",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_fragment_home_to_fragment_login);
            }

        }
    });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home,container,false);
        email3=view.findViewById(R.id.nine);
        btn3=view.findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.signOut();
            }
        });
        return view;
    }

}
