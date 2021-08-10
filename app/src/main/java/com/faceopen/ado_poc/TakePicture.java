package com.faceopen.ado_poc;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class TakePicture extends AppCompatActivity {
Button b1, b2 ;
ImageView iv_preview ;

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap bitmap;

    private Camera mCamera;
    private CameraPreview mPreview;

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


            }
        });






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



