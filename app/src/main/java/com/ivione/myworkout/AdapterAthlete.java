package com.ivione.myworkout;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class AdapterAthlete extends RecyclerView.Adapter<AdapterAthlete.ViewHolderAthletes> {

    List<Athlete> listAthletes;

    public AdapterAthlete(List<Athlete> listAthletes) {
        this.listAthletes = listAthletes;
    }

    @NonNull
    @Override
    public ViewHolderAthletes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_item, parent, false);
        return new ViewHolderAthletes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAthletes holder, int position) {
        AppDatabase db = Room.databaseBuilder(holder.itemView.getContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        holder.name.setText(listAthletes.get(position).name + " " + listAthletes.get(position).surname);
        holder.birthdate.setText(listAthletes.get(position).birthdate);
        holder.license.setText(listAthletes.get(position).license);
        holder.btnDelete.setOnClickListener(v -> {
            db.competitionDao().deleteCompetitionByLicense(listAthletes.get(position).license);
            db.athleteDao().delete(listAthletes.get(position));
            listAthletes.remove(position);
            notifyItemRemoved(position);
        });
        holder.btnAddCompetition.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), NewCompetition.class);
            intent.putExtra("license", listAthletes.get(position).license);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ListCompetitions.class);
            intent.putExtra("license", listAthletes.get(position).license);
            holder.itemView.getContext().startActivity(intent);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return listAthletes.size();
    }

    public class ViewHolderAthletes extends RecyclerView.ViewHolder {
        TextView name, birthdate, license;
        ImageButton btnDelete, btnAddCompetition;

        public ViewHolderAthletes(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            birthdate = itemView.findViewById(R.id.birthdate);
            license = itemView.findViewById(R.id.license);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnAddCompetition = itemView.findViewById(R.id.btnAddCompetition);
        }
    }
}
