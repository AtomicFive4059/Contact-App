package com.example.recyclerveiwapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ContactModel> modelArrayList = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingactionbutton);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_edit_contact);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAddEdit = dialog.findViewById(R.id.btnAddEdit);

                btnAddEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name = "",number = "";

                        if (!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Enter Contact Name..", Toast.LENGTH_SHORT).show();
                        }

                        if (!edtNumber.getText().toString().equals("")){
                            number = edtNumber.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }

                        if (!edtName.getText().toString().equals("") && !edtNumber.getText().toString().equals("")){

                            modelArrayList.add(new ContactModel(name,number));

                            adapter.notifyItemInserted(modelArrayList.size()-1);

                            recyclerView.scrollToPosition(modelArrayList.size()-1);

                            dialog.dismiss();
                        }


                    }
                });

                dialog.show();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        ContactModel contactModel = new ContactModel(R.drawable.woman0,"cat","0");

        modelArrayList.add(contactModel);
        modelArrayList.add(new ContactModel(R.drawable.cat,"A","1"));
        modelArrayList.add(new ContactModel(R.drawable.woman1,"B","2"));
        modelArrayList.add(new ContactModel(R.drawable.woman2,"C","3"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));
        modelArrayList.add(new ContactModel(R.drawable.woman3,"D","4"));

        adapter = new RecyclerViewAdapter(this,modelArrayList);
        recyclerView.setAdapter(adapter);
    }
}









