package tw.idv.chunhsin.class19_gesturephone;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnGoAddGesture, btnShowGestureList;
    GestureOverlayView gesture_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    void findViews() {
        btnGoAddGesture = (Button) findViewById(R.id.btnGoAddGesture);
        btnShowGestureList = (Button) findViewById(R.id.btnShowGestureList);
        gesture_main = (GestureOverlayView) findViewById(R.id.gesture_main);
        gesture_main.addOnGesturePerformedListener(onGesturePerformedListener);
    }

    public void clickToAddGesture(View view) {
        Intent intent = new Intent(MainActivity.this, addgesture.class);
        startActivity(intent);
    }

    public void clickToListGesture(View view) {
        Intent intent = new Intent(MainActivity.this, ListGestureActivity.class);
        startActivity(intent);
    }

    GestureOverlayView.OnGesturePerformedListener onGesturePerformedListener = new GestureOverlayView.OnGesturePerformedListener() {
        @Override
        public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            File file = new File(path, "myGesture");
            GestureLibrary library = GestureLibraries.fromFile(file);
            library.load();
            SharedPreferences phoneBook = getSharedPreferences("phoneBook", MODE_PRIVATE);
            ArrayList<Prediction> predictions = library.recognize(gesture);

            /*
            //測試用的Code
            StringBuilder sb=new StringBuilder();
            for(Prediction p:predictions){
                sb.append(p.name).append(" - ").append(p.score).append("\n");
            }
            Toast.makeText(MainActivity.this,sb,Toast.LENGTH_SHORT).show();
            */
            String gesName = predictions.get(0).name;
            String strPhone = "tel:" + phoneBook.getString(gesName, "");
            //到撥出電話的畫面
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(strPhone));
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);

        }
    };

}
