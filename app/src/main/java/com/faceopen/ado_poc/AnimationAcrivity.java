package com.faceopen.ado_poc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AnimationAcrivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_acrivity);
        BottomSheetDialog bottomSheet = new BottomSheetDialog();
        bottomSheet.setCancelable(false);
        if(!bottomSheet.isAdded()) {
            bottomSheet.show(this.getSupportFragmentManager(), bottomSheet.getTag());
        }
    }
}