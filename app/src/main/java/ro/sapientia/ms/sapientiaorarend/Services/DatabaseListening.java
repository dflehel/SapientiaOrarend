package ro.sapientia.ms.sapientiaorarend.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import com.google.firebase.database.*;
import ro.sapientia.ms.sapientiaorarend.Activity.MENU;
import ro.sapientia.ms.sapientiaorarend.Activity.MainScreen;
import ro.sapientia.ms.sapientiaorarend.Activity.Map;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.Util.Settings;
import ro.sapientia.ms.sapientiaorarend.models.User;

public class DatabaseListening extends IntentService {

    private DatabaseReference timetable;
    private DatabaseReference message;
    private DatabaseReference menu;
    private boolean firststarttimetable = true;
    private boolean firststartmenu = true;
    private boolean firststartmessage = true;


    public DatabaseListening(String name) {
        super(name);
        this.menu = FirebaseDatabase.getInstance().getReference("/menu");
        this.message = FirebaseDatabase.getInstance().getReference("/message");
    }

    public DatabaseListening() {
        super("szia");
        this.menu = FirebaseDatabase.getInstance().getReference("/menu");
        this.message = FirebaseDatabase.getInstance().getReference("/message");


    }


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DatabaseListening(String name, User u) {
        super(name);
        this.menu = FirebaseDatabase.getInstance().getReference("/menu");
        this.message = FirebaseDatabase.getInstance().getReference("/message");


    }
    // @androidx.annotation.Nullable


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (Settings.user != null) {
            this.timetable = FirebaseDatabase.getInstance().getReference("/timetables/" + Settings.user.getDeparment() + "/");
        }
        Intent intentTimetable = new Intent(this, MainScreen.class);
        PendingIntent pendingIntentTimetable = PendingIntent.getActivity(this, 0, intentTimetable, 0);
        Intent intentMenu = new Intent(this, MENU.class);
        PendingIntent pendingIntentMenu = PendingIntent.getActivity(this, 0, intentMenu, 0);
        Intent intentMessage = new Intent(this, Map.class);
        PendingIntent pendingIntentMessage = PendingIntent.getActivity(this, 0, intentMessage, 0);
        final Notification noti1 = new Notification.Builder(this)
                .setContentTitle("Valtozas")
                .setContentText("Orarendvaltozas tortent")
                .setSmallIcon(R.drawable.ic__update_timetable)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_update_time_table_round))
                .setContentIntent(pendingIntentTimetable)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build();
        final Notification noti2 = new Notification.Builder(this)
                .setContentTitle("Valtozas")
                .setContentText("UJ napi menu")
                .setSmallIcon(R.drawable.ic__update_restaurante)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_update_restaurante_round))
                .setContentIntent(pendingIntentMenu)
                //.setSound(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        final Notification noti3 = new Notification.Builder(this)
                .setContentTitle("Uzeneted erkezet")
                .setContentText("Orarendvaltozas tortent")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_message_update_round))
                .setSmallIcon(R.drawable.ic_message)
                .setContentIntent(pendingIntentMessage)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (Settings.user != null) {
          this.timetable.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!DatabaseListening.this.firststarttimetable) {
                        notificationManager.notify(1, noti1);
                        Intent intent = new Intent();
                        intent.setAction("time");
                        intent.putExtra("Dfdsfdf",1);
                        sendBroadcast(intent);
                    }
                    DatabaseListening.this.firststarttimetable = false;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        this.menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!DatabaseListening.this.firststartmenu) {
                    notificationManager.notify(1, noti2);
                    Intent intent = new Intent();
                    intent.setAction("menu");
                    intent.putExtra("Dfdsfdf",1);
                    sendBroadcast(intent);
                }
                DatabaseListening.this.firststartmenu = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        this.message.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!DatabaseListening.this.firststartmessage) {
                    notificationManager.notify(1, noti3);
                }
                DatabaseListening.this.firststartmessage = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    }
