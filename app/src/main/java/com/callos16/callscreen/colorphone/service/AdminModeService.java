package com.callos16.callscreen.colorphone.service;

import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AdminModeService {
    private static final String TAG = "AdminModeService";
    private static final String ADMIN_MODE_COLLECTION = "admin_settings";
    private static final String ADMIN_MODE_DOCUMENT = "mode";
    private static final String ADMIN_MODE_FIELD = "enabled";
    
    private FirebaseFirestore db;
    private AdminModeCallback callback;
    
    public interface AdminModeCallback {
        void onAdminModeChecked(boolean isAdminModeEnabled);
        void onError(String error);
    }
    
    public AdminModeService() {
        db = FirebaseFirestore.getInstance();
    }
    
    public void checkAdminMode(AdminModeCallback callback) {
        this.callback = callback;
        
        db.collection(ADMIN_MODE_COLLECTION)
            .document(ADMIN_MODE_DOCUMENT)
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Boolean adminModeEnabled = document.getBoolean(ADMIN_MODE_FIELD);
                        boolean isEnabled = adminModeEnabled != null && adminModeEnabled;
                        
                        Log.d(TAG, "Admin mode status: " + isEnabled);
                        if (callback != null) {
                            callback.onAdminModeChecked(isEnabled);
                        }
                    } else {
                        Log.d(TAG, "Admin mode document does not exist, defaulting to false");
                        if (callback != null) {
                            callback.onAdminModeChecked(false);
                        }
                    }
                } else {
                    Log.e(TAG, "Error getting admin mode status", task.getException());
                    if (callback != null) {
                        callback.onError("Failed to check admin mode: " + task.getException().getMessage());
                    }
                }
            });
    }
}
