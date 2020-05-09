package com.example.sekkawa7da.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sekkawa7da.Api.RetrofitClient;
import com.example.sekkawa7da.R;
import com.example.sekkawa7da.SharedPreferences.LoginResponse;
import com.example.sekkawa7da.SharedPreferences.PreferenceHelper;
import com.example.sekkawa7da.SharedPreferences.SharedPrefManager;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.username)
    TextInputLayout etUname;
    @BindView(R.id.password)
    TextInputLayout etPass;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.register)
    Button btnRegister;
    @BindView(R.id.terms)
    TextView terms;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        preferenceHelper = new PreferenceHelper(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnLogin){
            if (!validateUsername() | !validatePassword()) {
                return;
            }
            String username = etUname.getEditText().getText().toString().trim();
            String password = etPass.getEditText().getText().toString().trim();
            String grant_type = "password";

            //User user = new User(username,password,grant_type);

            Call<LoginResponse> call = RetrofitClient.getInstance().getLoginApi()
                    .getUserLogin(username, password, grant_type);

            //Toast.makeText(Login.this, username, Toast.LENGTH_LONG).show();
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();
                    try {
                        if (response.isSuccessful()) {
                            String res = loginResponse.getAccess_token();

                            //Toast.makeText(Login.this, res , Toast.LENGTH_LONG).show();
                            SharedPrefManager.getInstance(Login.this)
                                    .saveUser(loginResponse);  //
                            openHomeActivity();
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
        else if(view.getId()== R.id.register){
            Intent intent = new Intent(Login.this, Registeration.class);
            startActivity(intent);
            Login.this.finish();
        }
    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, MainPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /*private void loginUser() {



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.LOGINURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<String> call = api.getUserLogin(username,password);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        Toast.makeText(Login.this , response.body(),Toast.LENGTH_LONG).show();
                        String jsonresponse = response.body().toString();
                        parseLoginData(jsonresponse);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }*/

    private void parseLoginData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {

                saveInfo(response);

                Toast.makeText(Login.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveInfo(String response) {

        preferenceHelper.putIsLogin(true);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                    preferenceHelper.putUserName(dataobj.getString("UserName"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn())
            openHomeActivity();
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

        /*findViewById(R.id.forget).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        loginbtn = findViewById(R.id.login);
        register=findViewById(R.id.register);

        txt = findViewById(R.id.forget);*/


    /*public void onClick(View v) {
        if (v == loginbtn) {
            Intent in = new Intent(v.getContext(), Home.class);
            startActivity(in);
        }
        else if(v==txt)
        {
            Intent in1;
            in1 = new Intent(v.getContext(), Forgetpassword.class);
            startActivity(in1);
        }
        else if(v== register)
        {
            Intent in2;
            in2 = new Intent(v.getContext(), Registeration.class);
            startActivity(in2);
        }
    }*/
}
