package com.mahmoudrefaie.sekkawahda.ui.Registeration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient;
import com.mahmoudrefaie.sekkawahda.Pojo.LoginResponse;
import com.mahmoudrefaie.sekkawahda.R;
import com.mahmoudrefaie.sekkawahda.Pojo.User;
import com.mahmoudrefaie.sekkawahda.SharedPreferences.SharedPrefManager;
import com.mahmoudrefaie.sekkawahda.ui.Login.Login;
import com.mahmoudrefaie.sekkawahda.ui.MainPage.MainPage;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FragmentRegisterB extends Fragment implements View.OnClickListener {

    private TextInputLayout ssn , phone_no;
    private Spinner city;
    private CheckBox isAgree;

    private TextView previous;
    private Button registerBtn;

    private LoginResponse loginResponse;

    private SharedPreferences sharedPre;

    public FragmentRegisterB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_b, container, false);
        ssn = view.findViewById(R.id.ssn);
        phone_no = view.findViewById(R.id.phone);
        city = view.findViewById(R.id.city);
        isAgree = view.findViewById(R.id.agreeCheck);
        previous = view.findViewById(R.id.previous);
        previous.setOnClickListener(this);
        registerBtn = view.findViewById(R.id.regBtn);
        registerBtn.setOnClickListener(this);

        sharedPre = getActivity().getSharedPreferences("LOGIN",MODE_PRIVATE);

        //City Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.city_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);

        //Check if poivacy checkbox is chcked
        isAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    registerBtn.setEnabled(true);
                    registerBtn.setBackgroundColor(getResources().getColor(R.color.proj_sub_color));
                } else {
                    registerBtn.setEnabled(false);
                    registerBtn.setBackgroundColor(getResources().getColor(R.color.unclickable_Button));
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.previous){

            //To open second fragment
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentRegisterA firstFragment = new FragmentRegisterA();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.from_firstfrag_to_secondfrag,R.anim.exit_firstfrag_to_secondfrag,
                            R.anim.from_secondfrag_to_firstfrag,R.anim.exit_secondfrag_to_firstfrag)
                    .replace(R.id.fragment_register_layout , firstFragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();

        }
        else if(view.getId() == R.id.regBtn){
            if (!validateSSN() | !validatePhoneNo() | !validateCity()) {
                return;
            }

            String getSsn = ssn.getEditText().getText().toString().trim();
            String getPhone = phone_no.getEditText().getText().toString().trim();
            String getCity = city.getSelectedItem().toString();

            //Using Bundle to receive username, email, password data from another fragment
            String getUserName = getArguments().getString("username");
            String getEmail = getArguments().getString("email");
            String getPassword = getArguments().getString("password");

            makeRegister(getUserName,getEmail,getPassword,getSsn,getPhone,getCity);
        }
    }

    private void makeRegister(String username, String email, String pass, String ssn, String phone, String city){
        User user = new User(username, email, pass, ssn, phone, city);
        Call<String> call = RetrofitClient.getInstance().getApi().createUser(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.isSuccessful()) {
                        String res = response.body();
                        Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        makeLogin(username,pass);
                    } else {
                        String res = response.errorBody().string();
                        Toast.makeText(getActivity(), "Not Registered", Toast.LENGTH_LONG).show();
                        Log.e("Error Code", String.valueOf(response.code()));
                        Log.e("Error Body", res);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), "Check Your Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void makeLogin(String user, String pass){
        String type = "password";
        Call<LoginResponse> call = RetrofitClient.getInstance().getApi()
                .getUserLogin(user, pass, type);

        //Toast.makeText(Login.this, username, Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse = response.body();
                try {
                    if (response.isSuccessful()) {
                        SharedPrefManager.getInstance(getActivity()).saveUser(loginResponse);
                        openHomeActivity();
                        sharedPre.edit().putBoolean("logged",true).apply();
                    } else {
                        String res = response.errorBody().string();
                        Toast.makeText(getActivity(), "Incorrect username or password", Toast.LENGTH_LONG).show();
                        //Log.e("Error Code", String.valueOf(response.code()));
                        //Log.e("Error Body", response.errorBody().toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Internet isn't connect", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openHomeActivity() {
        Intent intent = new Intent(getActivity(), MainPage.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    private boolean validateSSN() {
        String valiSSN = ssn.getEditText().getText().toString().trim();

        if (valiSSN.isEmpty()) {
            ssn.setError("SSN can't be empty");
            return false;
        } else if (valiSSN.length() != 14 ) {
            ssn.setError("SSN must be 14 digits");
            return false;
        } else {
            ssn.setError(null);
            return true;
        }
    }

    private boolean validatePhoneNo() {
        String valiPhone = phone_no.getEditText().getText().toString().trim();

        if (valiPhone.isEmpty()) {
            phone_no.setError("SSN can't be empty");
            return false;
        } else if (!isValidPhone(valiPhone)) {
            phone_no.setError("Not valid phone number");
            return false;
        } else {
            phone_no.setError(null);
            return true;
        }
    }
    public boolean isValidPhone(CharSequence phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
    }

    private boolean validateCity(){
        String valiCity = city.getSelectedItem().toString();

        if(valiCity.isEmpty()){
            return false;
        }else if( valiCity.contains(":") ) {
            return false;
        }else{
            return true;
        }
    }

    private void openLoginPage(){
        Intent intent = new Intent(getActivity(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
