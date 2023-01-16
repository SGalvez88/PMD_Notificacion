package com.example.pmd_notificacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonAceptar = (Button) findViewById(R.id.botonAceptar);

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearNotificacion();
            }
        });
    }

    private void crearNotificacion() {
        String canalId = "Micanal01";
        int notificacionId = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence canalNombre = "Notificacion";
            NotificationChannel miCanal = null;

            miCanal = new NotificationChannel(canalId,canalNombre, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificador.createNotificationChannel(miCanal);

        }

        NotificationCompat.Builder creador = new NotificationCompat.Builder(getApplicationContext(), canalId);
        creador.setSmallIcon(android.R.drawable.stat_sys_warning);
        creador.setContentTitle("Notificacion de Android");
        creador.setContentText("Esto es un ejemplo de notificacion");
        creador.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        creador.setColor(Color.RED);
        creador.setDefaults(Notification.DEFAULT_SOUND);

        Intent intent = new Intent(MainActivity.this, Pantalla2.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        creador.setContentIntent(pendingIntent);

        NotificationManagerCompat notification = NotificationManagerCompat.from(getApplicationContext());
        notification.notify(notificacionId,creador.build());
    }
}