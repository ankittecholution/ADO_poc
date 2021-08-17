package com.faceopen.ado_poc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class ImageListPreview extends AppCompatActivity {
    ListView listView;
    ImageView iv1 ;
    private Bitmap[] imageid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list_preview);


      /*  listView=(ListView)findViewById(R.id.lv1);

       ListAdapter customList = new ListAdapter(this, imageid);
        listView.setAdapter(customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        }
        });
 */   }
}