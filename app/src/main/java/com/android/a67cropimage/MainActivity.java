package com.android.a67cropimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fenchtose.nocropper.CropperView;

public class MainActivity extends AppCompatActivity {

    Button btnCrop,btnToggleGesture;
    ImageView btnSnap,btnRotate;
    CropperView cropperView;
    Bitmap bitmap;
    boolean isSnappedtoCenter = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrop = findViewById(R.id.btnCrop);
        btnToggleGesture = findViewById(R.id.btnToogleGesture);
        btnSnap = findViewById(R.id.snap_button);
        btnRotate = findViewById(R.id.rotate_button);
        cropperView = findViewById(R.id.image);

        Bitmap originBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fahmi);
        cropperView.setImageBitmap(originBitmap);

        btnCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cropImage();
            }
        });

        btnToggleGesture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean enabled = cropperView.isGestureEnabled();
                enabled = !enabled;
                cropperView.setGestureEnabled(enabled);
                Toast.makeText(MainActivity.this, "Gesture"+(enabled?"Enabled":"Disabled"), Toast.LENGTH_SHORT).show();
            }
        });

        btnSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSnappedtoCenter){
                    cropperView.cropToCenter();
                }else{
                    cropperView.fitToCenter();
                }
                isSnappedtoCenter = !isSnappedtoCenter;
            }
        });

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cropperView.setImageBitmap(rotateBitmap(bitmap,90));
            }
        });

    }

    private Bitmap rotateBitmap(Bitmap bitmap, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    private void cropImage() {
        bitmap = cropperView.getCroppedBitmap();
        if (bitmap!=null){
            cropperView.setImageBitmap(bitmap);
        }
    }
}