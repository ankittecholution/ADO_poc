package com.faceopen.ado_poc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TakePicture extends AppCompatActivity {
Button b1, b2 ;
ImageView iv_preview ;

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap bitmap;
  //  private Bitmap[] imageid;
    ArrayList <Bitmap> imageid = new ArrayList<Bitmap>() ;
    private Camera mCamera;
    private CameraPreview mPreview;
    private RecyclerView listView;
    static Bitmap bmp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        b1 = (Button)findViewById(R.id.button_capture) ;

        iv_preview = (ImageView)findViewById(R.id.iv_preview) ;

        // Create an instance of Camera
        mCamera = getCameraInstance(this);
        mCamera.setDisplayOrientation(90);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.setPreviewCallback(new Camera.PreviewCallback() {
                    @Override
                    public void onPreviewFrame(byte[] bytes, Camera camera) {
                        Camera.Parameters parameters = camera.getParameters();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        YuvImage yuvImage = new YuvImage(bytes, parameters.getPreviewFormat(), parameters.getPreviewSize().width, parameters.getPreviewSize().height, null);
                        yuvImage.compressToJpeg(new Rect(0, 0, parameters.getPreviewSize().width, parameters.getPreviewSize().height), 90, out);
                        byte[] imageBytes = out.toByteArray();
                        bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                        try {
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                iv_preview.setImageBitmap(bitmap);
                imageid.add(bitmap) ;
                ListAdapter adapter = new ListAdapter(TakePicture.this, imageid);
                listView.setHasFixedSize(true);
                LinearLayoutManager layoutManager= new LinearLayoutManager(TakePicture.this,LinearLayoutManager.HORIZONTAL, false);
                listView.setLayoutManager(layoutManager);
                listView.setAdapter(adapter);

           /*     try {
                    iv_preview.setImageBitmap(bmp);
                    iv_preview.setVisibility(View.VISIBLE);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                } ;*/

            }
        });



        listView=(RecyclerView)findViewById(R.id.lv1);
        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = getCurrentItem();
                    iv_preview.setVisibility(View.VISIBLE);
                    iv_preview.setImageBitmap(imageid.get(position));
                }
            }
        });
        TextView t1 = (TextView)findViewById(R.id.cancel) ;
                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      iv_preview.setVisibility(View.GONE);
                    }
                    });



    }

    private int getCurrentItem(){
        return ((LinearLayoutManager)listView.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    public static Camera getCameraInstance(TakePicture context){
        Camera c = null;
        try {
            c = Camera.open(1); // attempt to get a Camera instance
        }
        catch (Exception e){
            Toast.makeText(context, "!"     + e.getMessage().toString(),  Toast.LENGTH_LONG).show();
        }
        return c; // returns null if camera is unavailable
    }

   }



