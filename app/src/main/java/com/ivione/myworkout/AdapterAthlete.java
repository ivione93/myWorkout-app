package com.ivione.myworkout;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class AdapterAthlete extends RecyclerView.Adapter<AdapterAthlete.ViewHolderAthletes> {

    List<Athlete> listAthletes;
    private static final int DURATION = 250;

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

        holder.unlinearl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.linearLayoutDetails.getVisibility() == View.GONE) {
                    // Cree la clase ExpandAndCollpaseViewUtil
                    ExpandAndCollapseViewUtil.expand(holder.linearLayoutDetails, DURATION);
                    holder.imageViewExpand.setImageResource(R.drawable.more);
                    // Rotamos el icono de flecha 180 grados
                    Animation animation = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setFillAfter(true);
                    animation.setDuration(DURATION);
                    holder.imageViewExpand.startAnimation(animation);

                } else {
                    ExpandAndCollapseViewUtil.collapse(holder.linearLayoutDetails, DURATION);
                    holder.imageViewExpand.setImageResource(R.drawable.less);
                    Animation animation = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setFillAfter(true);
                    animation.setDuration(DURATION);
                    holder.imageViewExpand.startAnimation(animation);

                }
            }
        });

        holder.btnAddCompetition.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), NewCompetition.class);
            intent.putExtra("license", listAthletes.get(position).license);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.btnShowCompetitions.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ListCompetitions.class);
            intent.putExtra("license", listAthletes.get(position).license);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listAthletes.size();
    }

    public class ViewHolderAthletes extends RecyclerView.ViewHolder {
        LinearLayout unlinearl;
        ViewGroup linearLayoutDetails;
        TextView name, birthdate, license;
        ImageView imageViewExpand;
        ImageButton btnDelete, btnAddCompetition, btnShowCompetitions;

        public ViewHolderAthletes(@NonNull View itemView) {
            super(itemView);
            unlinearl = itemView.findViewById(R.id.unlinearl);
            linearLayoutDetails = itemView.findViewById(R.id.linearLayoutDetails);
            name = itemView.findViewById(R.id.name);
            birthdate = itemView.findViewById(R.id.birthdate);
            license = itemView.findViewById(R.id.license);
            imageViewExpand = itemView.findViewById(R.id.imageViewExpand);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnAddCompetition = itemView.findViewById(R.id.btnAddCompetition);
            btnShowCompetitions = itemView.findViewById(R.id.btnShowCompetitions);
        }
    }
}
