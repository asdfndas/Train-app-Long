package com.example.trainticketproject.uis.Train;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Seat;
import com.example.trainticketproject.models.Status;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.uis.Seat.SeatAdapter;
import com.example.trainticketproject.utils.DateTimeConverter;
import com.example.trainticketproject.viewmodels.SeatViewModel;
import com.example.trainticketproject.viewmodels.StationViewModel;
import com.example.trainticketproject.viewmodels.TicketViewModel;
import com.example.trainticketproject.viewmodels.TrainStationViewModel;
import com.example.trainticketproject.viewmodels.TrainViewModel;
import com.example.trainticketproject.viewmodels.UserViewModel;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrainDetailInfoActivity extends AppCompatActivity {
    Long uid;
    Long trainId;
    private SeatViewModel seatViewModel;
    private TicketViewModel ticketViewModel;
    private UserViewModel userViewModel;
    private TrainViewModel trainViewModel;
    private StationViewModel stationViewModel;
    TextView tvTrip, tvDepartureStation, tvArrivalStation, tvDepartureTime, tvArrivalTime, tvTotalTime, tvPrice, tvDepartureDate, tvSeat;
    ImageButton imgBtnShare;

    RecyclerView recyclerView;
    private SeatAdapter seatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_detail_info);

        imgBtnShare = findViewById(R.id.imgBtnShare);
        tvTrip = findViewById(R.id.tvTrip);
        tvDepartureStation = findViewById(R.id.tvDepartureStation);
        tvArrivalStation = findViewById(R.id.tvArrivalStation);
        tvDepartureTime = findViewById(R.id.tvDepartureTime);
        tvArrivalTime = findViewById(R.id.tvArrivalTime);
        tvTotalTime = findViewById(R.id.tvTotalTime);
        tvPrice = findViewById(R.id.tvPrice);
        tvDepartureDate = findViewById(R.id.tvDepartureDate);
        tvSeat = findViewById(R.id.tvSeat);

        recyclerView = findViewById(R.id.recyclerVIewMap);

        trainViewModel = new ViewModelProvider(this).get(TrainViewModel.class);
        seatViewModel = new ViewModelProvider(this).get(SeatViewModel.class);
        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        stationViewModel = new ViewModelProvider(this).get(StationViewModel.class);

        Intent detailIntent = getIntent();
        uid = detailIntent.getLongExtra("uid", 1);
        trainId = detailIntent.getLongExtra("trainId", 1);
        Log.d("TrainDetail", "uid: " + uid);

        // Khi tạo train thì các seats nên được tạo cùng lúc đó
        new Thread(() -> {
            List<Seat> seats = createSeatsForTrain(trainId);
            seatViewModel.insertAllSeats(seats);
        }).start();

        int spanCount = 4;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);

        seatViewModel.getAllSeatsOfTrain(trainId).observe(this, seats -> {
            if (seats != null) {
                Log.d("TrainDetail in getAll SeatOfTrain", "uid: " + uid);
                seatAdapter = new SeatAdapter(uid, seats, seatViewModel, ticketViewModel, trainViewModel, userViewModel);
                recyclerView.setAdapter(seatAdapter);
            }
        });


        trainViewModel.getTrainById(trainId).observe(this, selectedTrain -> {
            if (selectedTrain != null) {
                updateUI(selectedTrain);
                trainViewModel.getAvailableSeatsCount(trainId).observe(this, availableSeats -> {
                    int totalSeats = selectedTrain.getTotalSeats();
                    availableSeats = totalSeats - availableSeats;
                    tvSeat.setText(String.valueOf(availableSeats) + "/" + String.valueOf(totalSeats));
                });

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    createNotificationDelayed(selectedTrain.getDepartureTime(), Duration.ofMinutes(1));
//                }
            }
        });
    }

    private void updateUI(Train selectedTrain) {
        tvTrip.setText(selectedTrain.getTrip());

        stationViewModel.getStationById(selectedTrain.getDepartureStationID()).observe(this, departureStation -> {
            if (departureStation != null) {
                tvDepartureStation.setText(departureStation.getStationName());
            }
        });

        stationViewModel.getStationById(selectedTrain.getArrivalStationID()).observe(this, arrivalStation -> {
            if (arrivalStation != null) {
                tvArrivalStation.setText(arrivalStation.getStationName());
            }
        });

        String formattedDepartureTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formattedDepartureTime = selectedTrain.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            tvDepartureTime.setText(formattedDepartureTime);
        }

        String formattedArrivalTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formattedArrivalTime = selectedTrain.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            tvArrivalTime.setText(formattedArrivalTime);
        }

        String formattedDate = DateTimeConverter.formatFullDate(selectedTrain.getDepartureTime());
        tvDepartureDate.setText(formattedDate);

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String priceString = numberFormat.format(selectedTrain.getPrice());
        tvPrice.setText(priceString + " VND");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Duration duration = Duration.between(selectedTrain.getDepartureTime(), selectedTrain.getArrivalTime());

            long totalMinutes = duration.toMinutes();
            long hours = totalMinutes / 60;
            long remainingMinutes = totalMinutes % 60;

            tvTotalTime.setText(hours + " hours and " + remainingMinutes + " mins");
        }

        imgBtnShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            String shareText = "Thông tin chuyến tàu:\n" +
                    "Trip: " + tvTrip.getText().toString()+ "\n" +
                    "Departure Station: " + tvDepartureStation.getText().toString() + "\n" +
                    "Arrival Station: " + tvArrivalStation.getText().toString() + "\n" +
                    "Departure Date: " + tvDepartureDate.getText().toString() + "\n" +
                    "Departure Time: " + tvDepartureTime.getText().toString() + "\n" +
                    "Price: " + tvPrice.getText().toString();

            sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, "Train Detail Information");
            startActivity(shareIntent);
        });
    }


    public static List<Train> createSampleTrains() {
        List<Train> trains = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            trains.add(new Train("HÀ NỘI - SÀI GÒN", 1L, 2L, LocalDateTime.now(), LocalDateTime.now().plusHours(2),200000, 40));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            trains.add(new Train("HÀ NỘI - ĐÀ LẠT", 1L,  3L, LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5), 200000, 40));
        }

        return trains;
    }

    public static List<Seat> createSeatsForTrain(Long trainId) {
        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= 40; ++i) {
            seats.add(new Seat(i, Status.AVAILABLE, trainId, null, null));
        }

        return seats;
    }

    public static List<Ticket> createSampleTickets() {
        List<Ticket> tickets = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tickets.add(new Ticket(1L, 1L, 1L, LocalDateTime.now(), Status.AVAILABLE));
            tickets.add(new Ticket(1L, 1L, 2L, LocalDateTime.now(), Status.AVAILABLE));
            tickets.add(new Ticket(2L, 1L, 3L, LocalDateTime.now(), Status.AVAILABLE));

            tickets.add(new Ticket(3L, 2L, 4L, LocalDateTime.now(), Status.AVAILABLE));
            tickets.add(new Ticket(3L, 2L, 5L, LocalDateTime.now(), Status.AVAILABLE));
            tickets.add(new Ticket(3L, 2L, 6L, LocalDateTime.now(), Status.AVAILABLE));
        }

        return tickets;
    }

//    private void createNotificationDelayed(LocalDateTime departureDate, Duration delay) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            LocalDateTime notificationTime = departureDate.minus(delay);
//
//            long notificationTimeMillis = notificationTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//            ScheduleNotification.scheduleNotification(getApplicationContext(), "Lịch trình của bạn", "Sắp tới ngày đi rồi!", notificationTimeMillis);
//        }
//    }
}