package com.example.trainticketproject.uis.Train;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Gender;
import com.example.trainticketproject.models.Station;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.uis.Ticket.MyTicketActivity;
import com.example.trainticketproject.uis.User.EditAccountActivity;
import com.example.trainticketproject.uis.Voucher.VoucherActivity;
import com.example.trainticketproject.utils.NotificationPublisher;
import com.example.trainticketproject.viewmodels.StationViewModel;
import com.example.trainticketproject.viewmodels.TrainStationViewModel;
import com.example.trainticketproject.viewmodels.TrainViewModel;
import com.example.trainticketproject.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrainActivity extends AppCompatActivity {
    Long uid;
    private TrainViewModel trainViewModel;
    private StationViewModel stationViewModel;

    private TrainStationViewModel trainStationViewModel;
    private UserViewModel userViewModel;
    private TrainAdapter adapter;
    RecyclerView recyclerViewTrain;
    private EditText edSearchTrain;
    private RadioButton rbDeparture, rbArrival;
    private Button btnSearchTrain, btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        NotificationPublisher.createNotificationChannel(this);

        trainViewModel = new ViewModelProvider(this).get(TrainViewModel.class);
        stationViewModel = new ViewModelProvider(this).get(StationViewModel.class);
        trainStationViewModel = new ViewModelProvider(this).get(TrainStationViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        //Search train handle
        edSearchTrain = findViewById(R.id.edSearchTrain);
        rbDeparture = findViewById(R.id.rbDeparture);
        rbArrival = findViewById(R.id.rbArrival);
        btnSearchTrain = findViewById(R.id.btnSearchTrain);
        btnClear = findViewById(R.id.btnClear);
        rbDeparture.setChecked(true);
        Intent intent = getIntent();
        if (intent != null) {
            uid = intent.getLongExtra("uid",1L);
            Log.d("TrainInActivity", "uid: " + uid);
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSearch();
            }
        });

        btnSearchTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTrain(edSearchTrain.getText().toString());
            }
        });
        // ADD SAMPLE TRAINS
        new Thread(() -> {
            List<Train> trains = TrainDetailInfoActivity.createSampleTrains();
            trainViewModel.insertMultipleTrains(trains);
        }).start();
        // ADD SAMPLE STATIONS
        new Thread(() -> {
            List<Station> stations = createSampleStations();
            stationViewModel.insertSampleStations(stations);
        }).start();
//        new Thread(() -> {
//            List<User> users = createSampleUser();
//            userViewModel.insertMultipleUser(users);
//        }).start();

        recyclerViewTrain = findViewById(R.id.recyclerViewTrain);

        trainViewModel.getAllTrains().observe(this, trains -> {
            adapter = new TrainAdapter(trains, trainViewModel, stationViewModel,uid);

            recyclerViewTrain.setAdapter(adapter);
        });


        recyclerViewTrain.setLayoutManager(new LinearLayoutManager(this));
    }

    private void searchTrain(String name) {
        if(rbDeparture.isChecked()) {
            stationViewModel.searchStationIDsByName(name).observe(this, stationIds -> {
                trainViewModel.getTrainsByDepartureStationIDs(stationIds).observe(this, trains -> {
                    adapter = new TrainAdapter(trains, trainViewModel, stationViewModel, uid);
                    recyclerViewTrain.setAdapter(adapter);
                });
            });
        } else if (rbArrival.isChecked()) {
            stationViewModel.searchStationIDsByName(name).observe(this, stationIds -> {
                trainViewModel.getTrainsByArrivalStationIDs(stationIds).observe(this, trains -> {
                    adapter = new TrainAdapter(trains, trainViewModel, stationViewModel, uid);
                    recyclerViewTrain.setAdapter(adapter);
                });
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_my_ticket) {
            Intent myTicketIntent = new Intent(this, MyTicketActivity.class);
            myTicketIntent.putExtra("uid", uid);

            startActivity(myTicketIntent);
        } else if (item.getItemId() == R.id.menu_item_all_voucher) {
            Intent allVouchersIntent = new Intent(this, VoucherActivity.class);
            allVouchersIntent.putExtra("uid", uid);
            startActivity(allVouchersIntent);
        } else if (item.getItemId() == R.id.menu_item_my_account) {
            Intent myAccountIntent = new Intent(this, EditAccountActivity.class);
            myAccountIntent.putExtra("uid", uid);
            startActivity(myAccountIntent);
        }
        return false;
    }

    public static List<User> createSampleUser() {
        List<User> users = new ArrayList<>();
        users.add(new User("john","John Doe", 25, Gender.MALE, "john@example.com", "password123", "123456789"));
        users.add(new User("jane","Jane Doe", 28, Gender.FEMALE, "jane@example.com", "password456", "987654321"));
        User dany = new User("danny","Danny", 22, Gender.FEMALE, "dany@example.com", "password456", "987654321");
        dany.setRewardPoint(10000);
        users.add(dany);

        return users;
    }
    private List<Station> createSampleStations() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("Hà Nội"));
        stations.add(new Station("Sài Gòn"));
        stations.add(new Station("Đà Lạt"));
        return stations;
    }

    private void clearSearch() {
        edSearchTrain.setText("");
        trainViewModel.getAllTrains().observe(this, trains -> {
            adapter = new TrainAdapter(trains, trainViewModel, stationViewModel,uid);

            recyclerViewTrain.setAdapter(adapter);
        });
        adapter.notifyDataSetChanged();
    }


}