package tw.idv.chunhsin.class19_gesturephone;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class addgesture extends AppCompatActivity {
    EditText etPhone,etGestureName;
    GestureOverlayView gesture_add;
    Button btnAddGesture,btnClearGesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgesture);
        findViews();
    }

    void findViews(){
        etGestureName = (EditText)findViewById(R.id.etGestureName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        btnAddGesture = (Button)findViewById(R.id.btnAddGesture);
        btnClearGesture = (Button)findViewById(R.id.btnClearGesture);
        gesture_add = (GestureOverlayView)findViewById(R.id.gesture_add);
        gesture_add.addOnGestureListener(gestureListener);
    }


    GestureOverlayView.OnGestureListener gestureListener = new GestureOverlayView.OnGestureListener() {
        @Override
        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
            btnAddGesture.setEnabled(false);
        }

        @Override
        public void onGesture(GestureOverlayView overlay, MotionEvent event) {

        }

        @Override
        public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
            Gesture gesture = overlay.getGesture();
            if(gesture.getLength()>0){
                String strPhoneNum=etPhone.getText().toString();
                String strGestureName=etGestureName.getText().toString();
                if(!strPhoneNum.equals("") && !strGestureName.equals("")){
                    btnAddGesture.setEnabled(true);
                }else{
                    btnAddGesture.setEnabled(false);
                }
            }else{
                btnAddGesture.setEnabled(false);
            }
        }

        @Override
        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
            btnAddGesture.setEnabled(false);
        }
    };

    public void addGesture(View view){
        //內部私有區
//        GestureLibrary library= GestureLibraries.fromPrivateFile(this,"myGesture");
        //外部公開區
        File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File file=new File(path,"myGesture");
        GestureLibrary library= GestureLibraries.fromFile(file);
        //讀取舊有的手勢
        library.load();
        //若有之前的手勢檔則先讀取後再儲存，否則只會儲入最後一個手勢
        Gesture gesture=gesture_add.getGesture();
        library.addGesture(etGestureName.getText().toString(),gesture);
        library.save();
    }

    public void clearGesture(View view){
        gesture_add.clear(true);
        btnAddGesture.setEnabled(false);
    }
}
