package com.callos16.callscreen.colorphone.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.callos16.callscreen.colorphone.R;
import com.callos16.callscreen.colorphone.databinding.ActivityContactdetailsBinding;


public class CallHis_ContactDetailActivity extends AppCompatActivity {

    ActivityContactdetailsBinding binding;
    String userName = "";
    String userNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);

        binding = ActivityContactdetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnBack.setOnClickListener(view -> onBackPressed());

        userName = getIntent().getStringExtra("username");
        userNumber = getIntent().getStringExtra("usernumber");

        binding.tvUsername.setText(userName);
        binding.tvUsernumber.setText(userNumber);
        binding.tvHomenumber.setText(userNumber);

    }
}