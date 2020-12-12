package com.example.firebase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firebase.Model.FireUserRepo;
import com.google.firebase.auth.FirebaseUser;

public class HomeViewModel extends AndroidViewModel {
    private FireUserRepo fireUserRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOUtMutableLiveData;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        fireUserRepo=new FireUserRepo(application);
        userMutableLiveData=fireUserRepo.getUserMutableLiveData();
        loggedOUtMutableLiveData=fireUserRepo.getLoggedOutMutableLiveData();

    }
    public void signOut(){
        fireUserRepo.logOut();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOUtMutableLiveData() {
        return loggedOUtMutableLiveData;
    }
}
