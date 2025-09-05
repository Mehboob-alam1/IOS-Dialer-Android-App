package com.callos16.callscreen.colorphone.admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.callos16.callscreen.colorphone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModeSelectionActivity extends AppCompatActivity {
    private static final String TAG = "ModeSelectionActivity";

    private ProgressBar progressBar;
    private Handler handler;
    private boolean modeChecked = false;
    private boolean isModeCheckStarted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mode_selection);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        handler = new Handler(Looper.getMainLooper());

        // Start admin mode check immediately
        maybeStartModeCheck();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
    }


    // requestDefaultDialer() now handled by DefaultDialerHelper



    // Removed ensureDefaultDialer(); use DefaultDialerHelper instead


    /**
     * If the app is launched via ACTION_DIAL / ACTION_CALL / tel: link,
     * forward the number to DialerActivity
     */


    private void checkAppMode() {
        // Since we're already in admin mode, proceed to admin flow
        modeChecked = true;
        Log.d(TAG, "Admin mode confirmed - proceeding to admin flow");
        
        // Navigate to admin authentication or main dashboard
        MyApplication.getInstance().routeToAdminFlow(ModeSelectionActivity.this, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void maybeStartModeCheck() {
        if (isModeCheckStarted) return;
        isModeCheckStarted = true;
        checkAppMode();
    }

}
