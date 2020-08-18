package com.mahmoudrefaie.sekkawahda.ui.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient;
import com.mahmoudrefaie.sekkawahda.R;
import com.mahmoudrefaie.sekkawahda.Pojo.LoginResponse;
import com.mahmoudrefaie.sekkawahda.SharedPreferences.PreferenceHelper;
import com.mahmoudrefaie.sekkawahda.SharedPreferences.SharedPrefManager;
import com.mahmoudrefaie.sekkawahda.ui.MainPage.MainPage;
import com.mahmoudrefaie.sekkawahda.ui.Registeration.Registeration;
import com.google.android.material.textfield.TextInputLayout;
/*import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
 */

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.squareup.picasso.Picasso;
 */

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_REQUIST_CODE = 2222; //Any number
    @BindView(R.id.username)
    TextInputLayout etUname;
    @BindView(R.id.password)
    TextInputLayout etPass;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.terms)
    TextView terms;

    private PreferenceHelper preferenceHelper;

    private LoginResponse loginResponse;

    private SharedPreferences sharedPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedPre = getSharedPreferences("LOGIN",MODE_PRIVATE);

        if(sharedPre.getBoolean("logged",false))
            openHomeActivity();

        btnLogin.setOnClickListener(this);
        register.setOnClickListener(this);
        preferenceHelper = new PreferenceHelper(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin){
            if (!validateUsername() | !validatePassword()) {
                return;
            }
            String username = etUname.getEditText().getText().toString().trim();
            String password = etPass.getEditText().getText().toString().trim();
            String grant_type = "password";

            Call<LoginResponse> call = RetrofitClient.getInstance().getApi()
                    .getUserLogin(username, password, grant_type);

            //Toast.makeText(Login.this, username, Toast.LENGTH_LONG).show();
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    loginResponse = response.body();
                    try {
                        if (response.isSuccessful()) {
                            String token = loginResponse.getAccess_token();
                            SharedPreferences.Editor editor = getSharedPreferences("MY_APP", MODE_PRIVATE).edit();
                            editor.putString("TOKEN", token);
                            editor.apply();

                            SharedPrefManager.getInstance(Login.this).saveUser(loginResponse);

                            openHomeActivity();  //Problem at main Page
                            sharedPre.edit().putBoolean("logged",true).apply();
                        } else {
                            String res = response.errorBody().string();
                            Toast.makeText(Login.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                            //Log.e("Error Code", String.valueOf(response.code()));
                            //Log.e("Error Body", response.errorBody().toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                       }
                }
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(Login.this, "Internet isn't connect", Toast.LENGTH_LONG).show();
                }
            });
        }
        else if(view.getId() == R.id.register){
            Intent intent = new Intent(Login.this, Registeration.class);
            startActivity(intent);
        }
    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, MainPage.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn())
            openHomeActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private boolean validateUsername() {
        String username = etUname.getEditText().getText().toString().trim();

        if (username.isEmpty()) {
            etUname.setError("Field can't be empty");
            return false;
        } else if (username.length() > 20) {
            etUname.setError("Username is too long");
            return false;
        } else {
            etUname.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String username = etPass.getEditText().getText().toString().trim();

        if (username.isEmpty()) {
            etPass.setError("Field can't be empty");
            return false;
        } else {
            etPass.setError(null);
            return true;
        }
    }

}