package com.saipc.ashish.instareplica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashish.instareplica.R;

public class LoginActivity extends Activity implements View.OnClickListener {

    Button btnLogin, btnSignUp;
    SessionManager manager;
    private EditText user_name_edtxt;
    private EditText passwd_edtxt;
    private String usr_name = null;
    private String pwd = null;
    private Button instaLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        manager = new SessionManager(getApplicationContext());

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        instaLoginBtn = (Button) findViewById(R.id.instagram_login_button);
        user_name_edtxt = (EditText) findViewById(R.id.user_name_editText);
        passwd_edtxt = (EditText) findViewById(R.id.password_editText);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        instaLoginBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent mainactivity = new Intent(LoginActivity.this, HomeActivity.class);
                usr_name = user_name_edtxt.getText().toString();
                pwd = passwd_edtxt.getText().toString();

                System.out.println("usr_name : " + usr_name);
                System.out.println("pwd : " + pwd);

                if (validate_feilds()) {
                    manager.createLoginSession(usr_name, pwd);
                    startActivity(mainactivity);
                } else {
                    Toast.makeText(getApplicationContext(), "Empty Feilds !", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_signup:
                Toast.makeText(this, "SignUp", Toast.LENGTH_SHORT).show();
                System.out.println("signup pressed");

            case R.id.instagram_login_button:
                Toast.makeText(this, "SignUp", Toast.LENGTH_SHORT).show();
                System.out.println("signup pressed");

                Intent webViewIntent = new Intent(getApplicationContext(), TestWebView.class);
                startActivity(webViewIntent);

                break;
            default:
                Toast.makeText(this, "NoView", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean validate_feilds() {

        if (usr_name.trim().length() > 0 && pwd.trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
