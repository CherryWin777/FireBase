package com.example.firebase.Model;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FireUserRepo {
    private Application application;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOutMutableLiveData;
    private DatabaseReference database;

    public FireUserRepo(Application application) {
        this.application = application;
        firebaseAuth=FirebaseAuth.getInstance();
        userMutableLiveData=new MutableLiveData<>();
        loggedOutMutableLiveData=new MutableLiveData<>();
        if(firebaseAuth.getCurrentUser()!=null){
            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
            loggedOutMutableLiveData.postValue(false);

        }

    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
    public void signUp(String email,String password,String userName){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                                    FirebaseUser fuser=firebaseAuth.getCurrentUser();
                                    String userid=fuser.getUid();
                                    database= FirebaseDatabase.getInstance().getReference("User").child(userid);
                                    HashMap<String, String> hash=new HashMap<>();
                                    hash.put("name",userName);
                                    hash.put("email",email);
                                    hash.put("id",userid);
                                    database.setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(application.getApplicationContext(),"Info Saved",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });

                        }else{
                            Toast.makeText(application.getApplicationContext(),"signUp filed"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
}
    public void loginIn(String email,String password,String userName){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                }else{
                    Toast.makeText(application.getApplicationContext(),"Login Filed"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        }

        public void logOut(){
        firebaseAuth.signOut();
        loggedOutMutableLiveData.postValue(true);
        }

    }


