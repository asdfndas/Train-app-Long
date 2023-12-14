package com.example.trainticketproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.example.trainticketproject.models.Ticket;

public class QRCodeGenerator {

    public static void generateQRCodeAsync(Context context, Ticket ticket, ImageView imageView) {
        new GenerateQRCodeAsyncTask(context, ticket, imageView).execute();
    }

    private static class GenerateQRCodeAsyncTask extends AsyncTask<Void, Void, Bitmap> {
        private final Context context;
        private final Ticket ticket;
        private final ImageView imageView;

        public GenerateQRCodeAsyncTask(Context context, Ticket ticket, ImageView imageView) {
            this.context = context;
            this.ticket = ticket;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Log.d("json", ticket.toJson());
            return generateQRCode(ticket.toJson());
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

        private Bitmap generateQRCode(String data) {
            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                return barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, 400, 400);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
