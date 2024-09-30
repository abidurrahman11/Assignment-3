package com.abidur.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText name, id, email, number;
    private Button btn;

    // Regex check
    private static final String emailRegex = "^(cse|ce|bua|arch|ist|eng|ban|thm|law)_[0-9]{10,16}@lus\\.ac\\.bd$";
    private static final String phoneRegex = "^(013|017|014|019|015|016|018|011)[0-9]{8}$";

    // Validate email
    public static boolean isValidEmail(String email) {
        return Pattern.matches(emailRegex, email);
    }
    // Validate phone number
    public static boolean isValidPhone(String phone) {
        return Pattern.matches(phoneRegex, phone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        id = findViewById(R.id.stid);
        email = findViewById(R.id.email);
        number = findViewById(R.id.phone);
        btn = findViewById(R.id.submit);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = name.getText().toString();
                String sid = id.getText().toString();
                String semail = email.getText().toString();
                String snumber = number.getText().toString();
                Boolean ok = true;
                if (sname.isEmpty()) {
                    ok = false;
                    name.setError("Enter Your Name");
                }
                if (sid.isEmpty()) {
                    ok = false;
                    id.setError("Enter Your ID");
                }
                if (semail.isEmpty() || !isValidEmail(semail)) {
                    ok = false;
                    email.setError("Enter a Valid Email");
                }
                if (snumber.isEmpty() || !isValidPhone(snumber)) {
                    ok = false;
                    number.setError("Enter a Valid Phone Number");
                }
                if (ok) {
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}