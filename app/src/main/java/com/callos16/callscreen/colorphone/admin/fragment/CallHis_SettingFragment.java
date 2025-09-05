package com.callos16.callscreen.colorphone.admin.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.callos16.callscreen.colorphone.admin.AdManager;
import com.callos16.callscreen.colorphone.admin.DownloadHistoryActivity;
import com.callos16.callscreen.colorphone.admin.RemoveAdsActivity;
import com.callos16.callscreen.colorphone.admin.WebActivity;
import com.callos16.callscreen.colorphone.databinding.FragmentCallHisSettingBinding;


public class CallHis_SettingFragment extends Fragment {

    FragmentCallHisSettingBinding binding;
    private PhoneAccountManager phoneAccountManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCallHisSettingBinding.inflate(getLayoutInflater());

        // Get PhoneAccountManager from parent activity
        if (getActivity() instanceof DialerHomeActivity) {
            phoneAccountManager = ((DialerHomeActivity) getActivity()).getPhoneAccountManager();
        }

        //FrameLayout bannerContainer = findViewById(R.id.banner_container);
        AdManager.loadBanner(requireContext(), binding.banner);

        binding.llPrivacy.setOnClickListener(view -> {
            Intent intent = new Intent(requireContext(), WebActivity.class);
            intent.putExtra("url", "https://easyranktools.com/privacy.html");
            startActivity(intent);
        });

        binding.llTerms.setOnClickListener(view -> {
            Intent intent = new Intent(requireContext(), WebActivity.class);
            intent.putExtra("url", "https://easyranktools.com/terms.html");
            startActivity(intent);
        });

        binding.llCancellation.setOnClickListener(view -> {
            Intent intent = new Intent(requireContext(), WebActivity.class);
            intent.putExtra("url", "https://easyranktools.com/cancellation.html");
            startActivity(intent);
        });
        binding.llShare.setOnClickListener(view -> {
            String packageName = requireActivity().getPackageName(); // get current app package name

            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", "I’ve use this Application. Download on Google Play..\n\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("https://play.google.com/store/apps/details?id=");
            sb2.append(packageName);
            intent.putExtra("android.intent.extra.TEXT", sb2.toString());
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                startActivity(Intent.createChooser(intent, "Share App..."));
            }
        });

        binding.llDownload.setOnClickListener(view -> {

            startActivity(new Intent(requireContext(), DownloadHistoryActivity.class));
        });

        binding.llRate.setOnClickListener(view -> {
            String packageName = requireActivity().getPackageName(); // get current app package name

            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id="+ packageName)));
            }catch (Exception e){
                Toast.makeText(getActivity(), "App not found", Toast.LENGTH_SHORT).show();
            }
        });

        binding.llRemoveAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireContext(), RemoveAdsActivity.class));
            }
        });

        binding.llDefaultDialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DefaultDialerHelper.isDefaultDialer(requireContext())) {
                    Toast.makeText(requireContext(), "Call Dialer is already your default dialer!", Toast.LENGTH_SHORT).show();
                } else {
                    // Show options to user
                    new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setTitle("Set as Default Dialer")
                        .setMessage("Choose how to set Call Dialer as your default dialer:")
                        .setPositiveButton("Try System Dialog", (dialog, which) -> {
                            Toast.makeText(requireContext(), "Requesting to set as default dialer...", Toast.LENGTH_SHORT).show();
                            // Reset cooldown for manual requests from settings
                            DefaultDialerHelper.resetRequestCooldown(requireContext());
                            // Use the existing DefaultDialerHelper which works properly
                            DefaultDialerHelper.requestToBeDefaultDialer(requireActivity(), 1001);
                        })
                        .setNegativeButton("Open Settings", (dialog, which) -> {
                            Toast.makeText(requireContext(), "Opening system settings...", Toast.LENGTH_SHORT).show();
                            // Open settings directly
                            DefaultDialerHelper.openDefaultDialerSettingsDirectly(requireActivity());
                        })
                        .setNeutralButton("Cancel", null)
                        .show();
                }
            }
        });

        return binding.getRoot();
    }
}