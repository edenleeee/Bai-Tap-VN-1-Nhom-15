package com.example.leeso.bai1_1420265;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final int GALLERY_PICK = 1;
    @BindView(R.id.TV)
    TextView mainTV;
    @BindView(R.id.changebackgroundBtn)
    TextView changeBackgroundBtn;
    @BindView(R.id.changetitleBtn)
    TextView changeTitleBtn;
    @BindView(R.id.imageView)
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String intentTV ;
        final String intentColor ;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            intentTV = extras.getString("mainTV");
            intentColor = extras.getString("mainColor");
            mainTV.setText(intentTV);
            if (intentColor.equals("HONG"))
                mainTV.setTextColor(getResources().getColor(R.color.colorAccent));
            if (intentColor.equals("XANH"))
                mainTV.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (intentColor.equals("VANG"))
                mainTV.setTextColor(getResources().getColor(R.color.VANG));
            if (intentColor.equals("DO"))
                mainTV.setTextColor(getResources().getColor(R.color.Do));
            if (intentColor.equals("XANHLA"))
                mainTV.setTextColor(getResources().getColor(R.color.XanhLa));
            if (intentColor.equals("NAU"))
                mainTV.setTextColor(getResources().getColor(R.color.Nau));

        }else {
            intentTV = "extra not set";
            intentColor = "HONG";
            mainTV.setText("BAI TAPPPP");
            mainTV.setTextColor(getResources().getColor(R.color.colorAccent));
        }




        changeTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ChangeTitleActivity.class);
                i.putExtra("mainTV",mainTV.getText());
                i.putExtra("mainColor",intentColor.toString());
                startActivity(i);
            }
        });
        changeBackgroundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent,"Select Image"),GALLERY_PICK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_PICK && resultCode == RESULT_OK){
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .start(this);
            //Toast.makeText(this, imageUri, Toast.LENGTH_LONG).show();
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

               /* mProgressDialog.setTitle("Uploading Image");
                mProgressDialog.setMessage("Please wait while we upload and process the image.");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();*/

                Uri resultUri = result.getUri();

                File thumb_filePath = new File(resultUri.getPath());

                Picasso.with(getApplicationContext()).load(resultUri).into(imageView);



                /*String user_id = mCurrentUser.getUid();

                Bitmap thumb_bitmap = null;

                try {
                    thumb_bitmap = new Compressor(getContext())
                            .setMaxWidth(200)
                            .setMaxHeight(200)
                            .setQuality(75)
                            .compressToBitmap(thumb_filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/


                /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                final byte[] thumb_byte = baos.toByteArray();

                StorageReference filepath = mImageStorage.child("profile_images").child(user_id+".jpg");
                final StorageReference thumb_filepath = mImageStorage.child("profile_images").child("thumbs").child(user_id+".jpg");
                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            final String download_url = task.getResult().getDownloadUrl().toString();
                            UploadTask uploadTask = thumb_filepath.putBytes(thumb_byte);
                            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                    String thumb_downloadUrl = task.getResult().getDownloadUrl().toString();

                                    if(task.isSuccessful()){
                                        Map update_hashMap = new HashMap<String, String>();
                                        update_hashMap.put("profileurl",download_url);
                                        update_hashMap.put("profileurl_thumb",thumb_downloadUrl);
                                        mUserDatabase.updateChildren(update_hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    mProgressDialog.dismiss();
                                                    Toast.makeText(getContext(), "Success in uploading", Toast.LENGTH_LONG).show();

                                                }
                                                else {

                                                }
                                            }
                                        });
                                    }
                                    else
                                    {
                                        mProgressDialog.dismiss();
                                        Toast.makeText(getContext(), "Error in uploading", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });



                        }
                        else {
                            mProgressDialog.dismiss();
                            Toast.makeText(getContext(), "Error in uploading", Toast.LENGTH_LONG).show();

                        }
                    }
                });*/

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
