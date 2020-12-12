package com.example.firebase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firebase.Model.FireUserRepo;
import com.google.firebase.auth.FirebaseUser;

public class loginViewModel extends AndroidViewModel {
    private FireUserRepo fireUserRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;

    public loginViewModel(@NonNull Application application) {
        super(application);

        fireUserRepo=new FireUserRepo(application);
        userMutableLiveData=fireUserRepo.getUserMutableLiveData();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void login(String email,String password,String userName){
        fireUserRepo.loginIn(email,password,userName);
    }
}
