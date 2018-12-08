package ro.sapientia.ms.sapientiaorarend.Services;

import android.app.*;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.google.firebase.database.*;
import ro.sapientia.ms.sapientiaorarend.MainScreen;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.models.User;

public class DatabaseListening extends IntentService {

    private DatabaseReference timetable;
    private DatabaseReference message;
    private DatabaseReference menu;
    private User user;

    public DatabaseListening(String name) {
        super(name);
        this.menu = FirebaseDatabase.getInstance().getReference("/menu");
        this.message = FirebaseDatabase.getInstance().getReference("/message");
    }

    public DatabaseListening(){
        super("szia");
        this.menu = FirebaseDatabase.getInstance().getReference("/menu");
        this.message = FirebaseDatabase.getInstance().getReference("/message");

    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DatabaseListening(String name,User u) {
        super(name);
        this.timetable = FirebaseDatabase.getInstance().getReference("/"+u.getDeparment());
        this.menu = FirebaseDatabase.getInstance().getReference("/menu");
        this.message = FirebaseDatabase.getInstance().getReference("/message");

    }
    // @androidx.annotation.Nullable


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent intent1 = new Intent(this,MainScreen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent1,0);
        final Notification noti1 = new Notification.Builder(this)
                .setContentTitle("Valtozas")
                .setContentText("Orarendvaltozas tortent")
                .setSmallIcon(R.drawable.ic_stat_adb )
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setContentIntent(pendingIntent)
                .build();
        final Notification noti2 = new Notification.Builder(this)
                .setContentTitle("Valtozas")
                .setContentText("UJ napi menu")
                .setSmallIcon(R.drawable.ic_stat_adb )
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setContentIntent(pendingIntent)
                //.setSound(Notification.DEFAULT_SOUND)
                .build();
        final Notification noti3 = new Notification.Builder(this)
                .setContentTitle("Uzeneted erkezet")
                .setContentText("Orarendvaltozas tortent")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setSmallIcon(R.drawable.ic_stat_adb )
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setContentIntent(pendingIntent)
                .build();
        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        /*        this.timetable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notification noti = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Valtozas")
                        .setContentText("Orarendvaltozas tortent")
                        .build();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        this.menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             notificationManager.notify(1, noti2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        this.message.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notificationManager.notify(1, noti3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
