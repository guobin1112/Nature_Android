package com.cn.balala.nature.nature.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cn.balala.nature.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PictureActivity extends AppCompatActivity {

    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;

    Button btnTakePhoto;
    Button btnChoosePhotoFromAlbum;
    ImageView ivPhoto;

    private Uri uriImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        btnTakePhoto = (Button) findViewById(R.id.btn_take_photo);
        btnChoosePhotoFromAlbum = (Button) findViewById(R.id.btn_choose_photo_from_album);
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String SDState = Environment.getExternalStorageState();
//                if (SDState.equals(Environment.MEDIA_MOUNTED)) {
//                    try {
//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        ContentValues values = new ContentValues();
//                        uriImage = getContentResolver().insert(
//                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uriImage);
//                        startActivityForResult(intent, TAKE_PHOTO);
//                    } catch (Exception e) {
//                        Toast.makeText(PictureActivity.this, "拍照异常，请检查系统设置",
//                                Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(PictureActivity.this, "内存卡不存在",
//                            Toast.LENGTH_LONG).show();
//                }

                File imgOutPut = new File(Environment.getExternalStorageDirectory(), "img_output.jpg");
                try {
                    if (imgOutPut.exists()) {
                        imgOutPut.delete();
                    }
                    imgOutPut.createNewFile();
                } catch (IOException e) {
                    Log.d("Nature", e.toString());
                    e.printStackTrace();
                }

                uriImage = Uri.fromFile(imgOutPut);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        btnChoosePhotoFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File imgOutPut = new File(Environment.getExternalStorageDirectory(), "img_output.jpg");
                try {
                    if (imgOutPut.exists()) {
                        imgOutPut.delete();
                    }
                    imgOutPut.createNewFile();
                } catch (IOException e) {
                    Log.d("Nature", e.toString());
                    e.printStackTrace();
                }

                uriImage = Uri.fromFile(imgOutPut);
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setDataAndType(uriImage, "image/*");
                intent.putExtra("scale", true);
                intent.putExtra("crop", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                startActivityForResult(intent, CROP_PHOTO);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uriImage, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(uriImage));
                        ivPhoto.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
