package com.callos16.callscreen.colorphone.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.callos16.callscreen.colorphone.admin.databinding.ActivityPhoneNumberBinding;


public class PhoneNumberActivity extends AppCompatActivity {

    ActivityPhoneNumberBinding binding;

    public static String phoneNumber = "";
    public static String countryCode = "";
    public static String userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        binding.btnBack.setOnClickListener(view -> onBackPressed());

        initClickEvent();
    }

    private void initClickEvent() {
        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnGetCallHistory.setOnClickListener(v -> {
            if (binding.inpNumber.getText().length() >= 8) {
                phoneNumber = binding.inpNumber.getText().toString();

                startActivity(new Intent(PhoneNumberActivity.this, SelectHistoryActivity.class));

            } else {
                Toast.makeText(PhoneNumberActivity.this, "Please enter valid phone number!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.ccp.setOnCountryChangeListener(() -> countryCode = binding.ccp.getSelectedCountryCode());

        countryCode = binding.ccp.getSelectedCountryCode();
    }
}
