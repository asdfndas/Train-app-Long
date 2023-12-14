package com.example.trainticketproject.uis.Ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.trainticketproject.R;
import com.example.trainticketproject.viewmodels.TicketViewModel;
import com.example.trainticketproject.viewmodels.UserViewModel;

public class MyTicketActivity extends AppCompatActivity {
    Long uid;
    private TicketViewModel ticketViewModel;
    private MyTicketAdapter adapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);

        recyclerView = findViewById(R.id.recyclerViewMyTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);

        Intent myTicketIntent = getIntent();
        uid = myTicketIntent.getLongExtra("uid", 1L);
        Log.d("MyTicketActivity", "uid: " + uid);

        try {
            ticketViewModel.getTicketsForUser(uid).observe(this, tickets -> {
                adapter = new MyTicketAdapter(tickets, ticketViewModel);
                recyclerView.setAdapter(adapter);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}