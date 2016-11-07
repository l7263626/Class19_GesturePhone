package tw.idv.chunhsin.class19_gesturephone;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnGoAddGesture,btnShowGestureList;
    GestureOverlayView gesture_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    void findViews(){
        btnGoAddGesture = (Button)findViewById(R.id.btnGoAddGesture);
        btnShowGestureList = (Button)findViewById(R.id.btnShowGestureList);
        gesture_main = (GestureOverlayView)findViewById(R.id.gesture_main);
    }

    public void clickToAddGesture(View view){
        Intent intent = new Intent(MainActivity.this,addgesture.class);
        startActivity(intent);
    }

    public void clickToListGesture(View view){
        Intent intent = new Intent(MainActivity.this,ListGestureActivity.class);
        startActivity(intent);
    }

}
