package com.bgmiddleeast.Fcm;

/**
 * Created by sr034 on 11-Aug-16.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.bgmiddleeast.Activity.NotificationActivity;
import com.bgmiddleeast.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    String messages, push_flag, image;
    public static int NOTIFICATION_ID;
    Intent intent;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Map data = remoteMessage.getData();

        System.out.println( "Remote message " + remoteMessage.toString() );
        String message = (String) data.get( "message" );

        sendNotification( "BgMiddleEast" );

//
//        try {

//            JSONObject dataObject	= new JSONObject(message);
//            messages=dataObject.optString("message").toString();
//            push_flag = dataObject.optString("push_flag").toString();
//            if(dataObject.has("image")) {
//                image = dataObject.optString("image").toString();
//                Model.pushImage=image;
//            }
//        }catch (JSONException e){
//            System.out.println("JSON Exception="+e.toString());
//        }


//        Log.d(TAG, "Message: " + message);
//        System.out.println("ParseGCM---"+message);
//        Log.d(TAG, "From: " + remoteMessage.getFrom());

//       if(push_flag.equals("NORMAL")){

//            intent = new Intent(this, ShowNotificationActivity.class);
//            intent. addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);

//        }else
//        sendNotification(messages);
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d( TAG, "Message data payload: " + remoteMessage.getData() );
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d( TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody() );

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {

        Random random = new Random();
        NOTIFICATION_ID = random.nextInt( 9999 - 1000 ) + 1000;

        intent = new Intent( this, NotificationActivity.class );
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );

        PendingIntent pendingIntent = PendingIntent.getActivity( this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT );
        Bitmap large_icon = BitmapFactory.decodeResource( getResources(), R.drawable.icon );
        //  Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder( this )
                .setSmallIcon( R.drawable.icon )
                .setLargeIcon( large_icon )
                .setContentTitle( "BGMiddleEast" )
                .setContentText( messageBody )
                .setAutoCancel( true )
                .setDefaults( Notification.DEFAULT_ALL )
                .setContentIntent( pendingIntent );

        NotificationManager notificationManager =
                (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );

        notificationManager.notify( NOTIFICATION_ID, notificationBuilder.build() );
    }
}