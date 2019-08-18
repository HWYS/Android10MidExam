package com.hwys.android10midexam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.hwys.android10midexam.R;
import com.hwys.android10midexam.db.model.PostModel;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<PostModel> postModelList;

    public PostAdapter(List<PostModel> postModelList) {
        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvUserName.setText(postModelList.get(position).getU_name());
        holder.tvStatus.setText(postModelList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvStatus, tvUserName;
        MaterialButton btnUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
