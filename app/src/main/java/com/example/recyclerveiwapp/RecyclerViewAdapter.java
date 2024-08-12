package com.example.recyclerveiwapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactModel> modelArrayList;
    private ViewHolder holder;
    private int position;

    public RecyclerViewAdapter(Context context, ArrayList<ContactModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.img.setImageResource(modelArrayList.get(position).img);
        holder.txtName.setText(modelArrayList.get(position).name);
        holder.txtNumber.setText(modelArrayList.get(position).number);

        holder.rootLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_edit_contact);

                TextView txtLabel = dialog.findViewById(R.id.txtLabel);
                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction = dialog.findViewById(R.id.btnAddEdit);

                txtLabel.setText("Update Contact Details");
                btnAction.setText("Upgate Details");

               edtName.setText(modelArrayList.get(position).name);
               edtNumber.setText(modelArrayList.get(position).number);



               btnAction.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String name = "",number = "";

                       if (!edtName.getText().toString().equals("")){
                           name = edtName.getText().toString();
                       }else {
                           Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show();
                       }

                       if (!edtNumber.getText().toString().equals("")){
                           number = edtNumber.getText().toString();
                       }else {
                           Toast.makeText(context, "Please Enter Number", Toast.LENGTH_SHORT).show();
                       }

                       modelArrayList.set(position,new ContactModel(name,number));
                       notifyItemChanged(position);

                       dialog.dismiss();
                   }
               });
                dialog.show();
            }
        });


        holder.rootLinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder= new AlertDialog.Builder(context)
                        .setTitle("Delete Contact...")
                        .setIcon(R.drawable.cat)
                        .setMessage("Sure To Delete Contact!")
                        .setPositiveButton("Yeah", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                modelArrayList.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txtName;
        TextView txtNumber;
        LinearLayout rootLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             txtName = itemView.findViewById(R.id.contactTxt);
             txtNumber = itemView.findViewById(R.id.numberTxt);
             img = itemView.findViewById(R.id.profileImg);
             rootLinearLayout = itemView.findViewById(R.id.rootLinearLayout);
        }
    }
}
