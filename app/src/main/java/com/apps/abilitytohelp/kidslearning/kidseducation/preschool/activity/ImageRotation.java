package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;

import androidx.exifinterface.media.ExifInterface;

import java.io.IOException;
import java.util.Objects;

public class ImageRotation {
    public static Bitmap fixBitmapOrientation(Uri uri, Bitmap bmp, Activity activity)
            throws IOException {
        ExifInterface ei = new ExifInterface(Objects.requireNonNull(activity.getContentResolver().openInputStream(uri)));
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateBitmap(bmp, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateBitmap(bmp, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateBitmap(bmp, 270);
        }

        return bmp;
    }

    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
                source.getHeight(), matrix, true);
    }
}
