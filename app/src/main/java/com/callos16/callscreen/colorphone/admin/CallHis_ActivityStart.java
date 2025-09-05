package com.callos16.callscreen.colorphone.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.callos16.callscreen.colorphone.R;
import com.callos16.callscreen.colorphone.databinding.ActivityStartBinding;


public class CallHis_ActivityStart extends AppCompatActivity {

    ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        initClickEvent();
    }

    private void initClickEvent() {
        binding.btnGetHistory.setOnClickListener(v -> {
          startActivity(new Intent(CallHis_ActivityStart.this,PhoneNumberActivity.class));
        });
    }

}