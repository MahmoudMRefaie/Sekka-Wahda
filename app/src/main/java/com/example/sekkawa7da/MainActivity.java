package com.example.sekkawa7da;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {
    private EditText etUname, etPass;
    private Button btnLogin;
    private Button btnRegister;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceHelper = new PreferenceHelper(this);

        etUname = (EditText) findViewById(R.id.username);
        etPass = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.register);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUname.getText().toString();
                String password = etPass.getText().toString();
                String grant_type = "password";

                //User user = new User(username,password,grant_type);

                Call<LoginResponse> call = RetrofitClient.getInstance().getLoginApi()
                        .getUserLogin(username,password,grant_type);

                //Toast.makeText(MainActivity.this, username, Toast.LENGTH_LONG).show();
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        try {
                            if (response.isSuccessful()) {
                                String res = loginResponse.getAccess_token();

                                //Toast.makeText(MainActivity.this, res , Toast.LENGTH_LONG).show();
                                SharedPrefManager.getInstance(MainActivity.this)
                                        .saveUser(loginResponse);  //
                                openHomeActivity();
                            } else {
                                String res = response.errorBody().string();
                                Toast.makeText(MainActivity.this, "Incorrect username or password" , Toast.LENGTH_LONG).show();
                                //Log.e("Error Code", String.valueOf(response.code()));
                                //Log.e("Error Body", response.errorBody().toString());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Internet isn't connect",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registeration.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this , Home.class);
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
                        Toast.makeText(MainActivity.this , response.body(),Toast.LENGTH_LONG).show();
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

    private void parseLoginData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {

                saveInfo(response);

                Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveInfo(String response){

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
        if(SharedPrefManager.getInstance(this).isLoggedIn())
            openHomeActivity();
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
