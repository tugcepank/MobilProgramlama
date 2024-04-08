package com.nexis.deneme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GirisActivity extends AppCompatActivity {

    private EditText editEmail, editSifre;
    private String txtEmail,txtSifre;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_giris);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editEmail=(EditText) findViewById(R.id.giris_yap_editEmail);
        editSifre=(EditText) findViewById(R.id.giris_yap_editSifre);

        mAuth=FirebaseAuth.getInstance();
    }

    public void GirisYap(View view){
        txtEmail=editEmail.getText().toString();
        txtSifre=editSifre.getText().toString();

        if (!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtSifre)){
            mAuth.signInWithEmailAndPassword(txtEmail,txtSifre)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser=mAuth.getCurrentUser();
                            System.out.println("Kullanıcı Adı:"+mUser.getDisplayName());
                            System.out.println("Kullanıcı email:"+mUser.getEmail());
                            System.out.println("Kullanıcı Uid:"+mUser.getUid());
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(GirisActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else {
            Toast.makeText(this,"Email Ve Şifre Boş Olamaz",Toast.LENGTH_SHORT).show();
        }
    }

    public void kayitOl_2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}