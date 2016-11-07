package tw.idv.chunhsin.class19_gesturephone;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by student on 2016/11/7.
 */

public class GestureAdapter extends BaseAdapter {
    Context context;
    ArrayList<HashMap<String,Object>> data;

    public GestureAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //做convertView的部份
        MyTag myTag=new MyTag();
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_view,null);
            myTag.image=(ImageView)convertView.findViewById(R.id.imageView);
            myTag.tvGes=(TextView)convertView.findViewById(R.id.textView7);
            myTag.tvPhoneNum=(TextView)convertView.findViewById(R.id.textView8);
            convertView.setTag(myTag);
        }else {
            myTag=(MyTag)convertView.getTag();
        }

        //設定資料的部份.
        myTag.image.setImageBitmap((Bitmap)data.get(position).get("gesPic"));
        myTag.tvGes.setText((String)data.get(position).get("gesName"));
        myTag.tvPhoneNum.setText((String)data.get(position).get("phoneNum"));
        return convertView;
    }

    class MyTag{
        ImageView image;
        TextView tvGes;
        TextView tvPhoneNum;
    }
}
