package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
EditText userName,password,repassword;
Button singUp,singIn;
Dbhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        singUp=findViewById(R.id.btnSingup);
        singIn=findViewById(R.id.btnSingin);
        db=new Dbhelper(this);
        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=userName.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass .equals("")){
                    Toast.makeText(Register.this, "Nhập thông tin!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser=db.checkusername(user);
                        if(checkuser==false){
                            Boolean insert=db.insertUsers(user,pass);
                            if(insert==true){
                                Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Register.this, "Đăng ký lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Register.this, "vui lòng sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Register.this, "Password không đúng!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}