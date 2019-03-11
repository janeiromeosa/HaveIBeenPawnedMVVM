package com.example.haveibeenpawnedmvvm.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haveibeenpawnedmvvm.R;
import com.example.haveibeenpawnedmvvm.model.HaveIBeenPawnedRepo;

import java.util.ArrayList;
import java.util.List;

public class HaveIBeenPawnedAdapter extends RecyclerView.Adapter<HaveIBeenPawnedAdapter.PawnedViewHolder> {

    private final List<HaveIBeenPawnedRepo> haveIBeenPawnedRepos= new ArrayList<>();

    public void setData(List<HaveIBeenPawnedRepo> data){
        haveIBeenPawnedRepos.clear();
        haveIBeenPawnedRepos.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PawnedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
        return new PawnedViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull PawnedViewHolder pawnedViewHolder, int position) {
        HaveIBeenPawnedRepo haveIBeenPawnedRepo = haveIBeenPawnedRepos.get(position);
        pawnedViewHolder.tvName.setText(haveIBeenPawnedRepo.getName().toString());
        pawnedViewHolder.tvTitle.setText(haveIBeenPawnedRepo.getTitle().toString());
        pawnedViewHolder.tvDomain.setText(haveIBeenPawnedRepo.getDomain().toString());
        pawnedViewHolder.tvBreachDate.setText(haveIBeenPawnedRepo.getBreachDate().toString());
        pawnedViewHolder.tvAddedDate.setText(haveIBeenPawnedRepo.getAddedDate().toString());
        pawnedViewHolder.tvModifiedDate.setText(haveIBeenPawnedRepo.getModifiedDate().toString());
    }

    @Override
    public int getItemCount() {
        return haveIBeenPawnedRepos.size();
    }

    static class PawnedViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvTitle;
        TextView tvDomain;
        TextView tvBreachDate;
        TextView tvDescription;
        TextView tvAddedDate;
        TextView tvModifiedDate;

        public PawnedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDomain = itemView.findViewById(R.id.tvDomain);
            tvBreachDate = itemView.findViewById(R.id.tvBreachDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAddedDate = itemView.findViewById(R.id.tvAddedDate);
            tvModifiedDate = itemView.findViewById(R.id.tvModifiedDate);
        }
    }
}
