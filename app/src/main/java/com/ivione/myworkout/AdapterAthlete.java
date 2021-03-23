package com.ivione.myworkout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAthlete extends RecyclerView.Adapter<AdapterAthlete.ViewHolderAthletes> {

    ArrayList<Athlete> listAthletes;

    public AdapterAthlete(ArrayList<Athlete> listAthletes) {
        this.listAthletes = listAthletes;
    }

    @NonNull
    @Override
    public ViewHolderAthletes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_item, null, false);
        return new ViewHolderAthletes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAthletes holder, int position) {
        holder.name.setText(listAthletes.get(position).name + " " + listAthletes.get(position).surname);
        holder.birthdate.setText(listAthletes.get(position).birthdate);
        holder.license.setText(listAthletes.get(position).license);
    }

    @Override
    public int getItemCount() {
        return listAthletes.size();
    }

    public class ViewHolderAthletes extends RecyclerView.ViewHolder {

        TextView name, birthdate, license;

        public ViewHolderAthletes(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            birthdate = itemView.findViewById(R.id.birthdate);
            license = itemView.findViewById(R.id.license);
        }
    }
}
