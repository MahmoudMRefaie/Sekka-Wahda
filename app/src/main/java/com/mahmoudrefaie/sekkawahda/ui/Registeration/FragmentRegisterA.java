package com.mahmoudrefaie.sekkawahda.ui.Registeration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahmoudrefaie.sekkawahda.R;
import com.google.android.material.textfield.TextInputLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegisterA extends Fragment implements View.OnClickListener {

    private TextInputLayout username , email , password;
    private TextView next;

    public FragmentRegisterA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_a, container, false);

        username = view.findViewById(R.id.user);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.pass);

        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.next) {
            if (!validateUsername() | !validatePassword() | !validateEmail()) {
                return;
            }

            String getUserName = username.getEditText().getText().toString().trim();
            String getEmail = email.getEditText().getText().toString().trim();
            String getPassword = password.getEditText().getText().toString().trim();

            //Using Bundle  to send username, email, password data to another fragment
            Bundle bundle=new Bundle();
            bundle.putString("username", getUserName);
            bundle.putString("email", getEmail);
            bundle.putString("password", getPassword);

            //To open second fragment
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentRegisterB secondFragment = new FragmentRegisterB();
            secondFragment.setArguments(bundle);  //send data to another fragment
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.from_secondfrag_to_firstfrag,R.anim.exit_secondfrag_to_firstfrag,
                            R.anim.from_firstfrag_to_secondfrag,R.anim.exit_firstfrag_to_secondfrag)
                    .replace(R.id.fragment_register_layout , secondFragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();

        }
    }

    private boolean validateUsername() {
        String valiUsername = username.getEditText().getText().toString().trim();

        if (valiUsername.isEmpty()) {
            username.setError("Username can't be empty");
            return false;
        } else if (valiUsername.length() > 20) {
            username.setError("Username is too long");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String valiEmail = email.getEditText().getText().toString().trim();

        if (valiEmail.isEmpty()) {
            email.setError("Email can't be empty");
            return false;
        } else if ( !isValidEmailAddress(valiEmail) ) {
            email.setError("Not valid email");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    public boolean isValidEmailAddress(String email) {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailPattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    private boolean validatePassword() {
        String valiPasword = password.getEditText().getText().toString().trim();

        if (valiPasword.isEmpty()) {
            password.setError("Password can't be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}
