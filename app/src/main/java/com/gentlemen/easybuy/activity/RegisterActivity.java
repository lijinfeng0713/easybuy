package com.gentlemen.easybuy.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gentlemen.easybuy.R;
import com.gentlemen.easybuy.service.UserRegister;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editPassword;
    private EditText passwordConfirm;
    private EditText editPhone;
    private EditText editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUsername = (EditText) findViewById(R.id.register_username);
        editPassword = (EditText) findViewById(R.id.register_password);
        passwordConfirm = (EditText) findViewById(R.id.passConfirm);
        editPhone = (EditText) findViewById(R.id.phone);
        editAddress = (EditText) findViewById(R.id.address);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String pwdConfirm = passwordConfirm.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String address = editAddress.getText().toString().trim();

                if ("".equals(username) || "".equals(password) || "".equals(pwdConfirm)
                        || "".equals(phone) || "".equals(address)) {
                    Toast.makeText(RegisterActivity.this, "注册信息不能为空",Toast.LENGTH_SHORT).show();
                } else if (password.equals(pwdConfirm)) {
                    register(username,password,phone,address);
                } else {

                }
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register(String username, String password, String phone, String address) {
        UserRegister task = new UserRegister(username,password,phone,address);

        try {
            final String result = task.execute().get();
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (result.equals("true")) {
                        Toast.makeText(RegisterActivity.this, "注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this,"注册失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
