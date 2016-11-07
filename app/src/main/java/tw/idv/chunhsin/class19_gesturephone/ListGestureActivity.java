package tw.idv.chunhsin.class19_gesturephone;

import android.content.SharedPreferences;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ListGestureActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gesture);
        findViews();
        showListView();
    }

    void findViews(){
        listView = (ListView)findViewById(R.id.listView);
    }

    void showListView(){
        File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File file=new File(path,"myGesture");
        GestureLibrary library= GestureLibraries.fromFile(file);
        SharedPreferences phoneBook = getSharedPreferences("phoneBook", MODE_PRIVATE);
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        if(library.load()) {
            Set<String> gesNames = library.getGestureEntries();
            int i = 0;
            for (String gesName : gesNames) {
                ArrayList<Gesture> gestures = library.getGestures(gesName);
                for (Gesture ges : gestures) {
                    i++;
                    HashMap<String, Object> items = new HashMap<>();
                    items.put("gesPic", ges.toBitmap(80, 80, 5, Color.BLUE));
                    items.put("gesName", gesName);
                    items.put("phoneNum", phoneBook.getString(gesName, ""));
                    data.add(items);
                }
            }
            GestureAdapter gestureAdapter = new GestureAdapter(this, data);
            listView.setAdapter(gestureAdapter);
        }
    }
}
