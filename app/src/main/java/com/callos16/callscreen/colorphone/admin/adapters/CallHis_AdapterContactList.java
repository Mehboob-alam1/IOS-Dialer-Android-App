package com.callos16.callscreen.colorphone.admin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.callos16.callscreen.colorphone.R;
import com.callos16.callscreen.colorphone.admin.CallHis_ContactDetailActivity;
import com.callos16.callscreen.colorphone.admin.database.User;
import com.callos16.callscreen.colorphone.databinding.ItemContactListBinding;


import java.util.List;

public class CallHis_AdapterContactList extends RecyclerView.Adapter<CallHis_AdapterContactList.ContactAdapterViewHolder> {

    Context context;
    List<User> user;

    public CallHis_AdapterContactList(Context context, List<User> user) {
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public ContactAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.tvUserName.setText(user.get(position).username);
        holder.binding.tvUserNumber.setText(user.get(position).mobile);

        holder.binding.ivMakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall(user.get(position).mobile);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CallHis_ContactDetailActivity.class).putExtra("username",user.get(position).username)
                        .putExtra("usernumber",user.get(position).mobile));
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    class ContactAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemContactListBinding binding;

        public ContactAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemContactListBinding.bind(itemView);
        }
    }

    private void makeCall(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String temp = "tel:" + number;
        intent.setData(Uri.parse(temp));
        context.startActivity(intent);
    }
}
