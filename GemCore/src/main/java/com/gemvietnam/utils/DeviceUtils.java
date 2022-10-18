package com.gemvietnam.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;

import com.gemvietnam.base.log.Logger;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Device Utils
 * Created by neo on 2/16/2016.
 */
public class DeviceUtils {

  public static ContentResolver contentResolver;
  private DeviceUtils() {

  }



  public static Point getDeviceSize(Activity context) {
    Display display = context.getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size;
  }

  public static Point getDeviceSizePortrait(Activity context) {
    Display display = context.getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);

    int x = Math.min(size.x, size.y);
    int y = Math.max(size.x, size.y);
    return new Point(x, y);
  }

  public static int getDpi(Context context) {
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    return (int) (metrics.density * 160f);
  }

  public static boolean isLandscape(Activity activity) {
    return activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
  }

  public static boolean isActivityAutoRotate(Activity activity) {
    return activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_UNDEFINED;
  }

  /**
   * Force set the orientation of activity
   *
   * @param activity    target activity
   * @param orientation 1 of those values
   *                    Configuration.ORIENTATION_LANDSCAPE
   *                    or Configuration.ORIENTATION_PORTRAIT
   *                    or Configuration.ORIENTATION_UNDEFINED
   */
  public static void forceRotateScreen(Activity activity, int orientation) {
    switch (orientation) {
      case Configuration.ORIENTATION_LANDSCAPE:
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        break;
      case Configuration.ORIENTATION_PORTRAIT:
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        break;
      case Configuration.ORIENTATION_UNDEFINED:
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        break;
      default:
        break;
    }
  }

  public static boolean isDeviceLockRotate(Context context) {
    final int rotationState = Settings.System.getInt(
        context.getContentResolver(),
        Settings.System.ACCELEROMETER_ROTATION, 0
    );

    return rotationState == 0;
  }



  public static void openAppInStore(Context context) {
    final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
    try {
      context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
    } catch (android.content.ActivityNotFoundException anfe) {
      Logger.e("Error", "ActivityNotFoundException", anfe);
      context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
    }
  }

  // A method to find height of the status bar
  public static int getStatusBarHeight(Context context) {
    int result = 0;
    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = context.getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }

  public static int getActionBarHeight(Context context) {
    // Calculate ActionBar height
    TypedValue tv = new TypedValue();
    if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
      return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
    }

    return 0;
  }

  public static int dpToPx(Context context, int dp) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
  }

  public static int pxToDp(Context context, int px){
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
  }

  public static String getContactDisplayNameByNumber(Context context ,String number) {
    Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
    String name = number;

//    ContentResolver contentResolver = context.getContentResolver();
    if (contentResolver == null){
      contentResolver = context.getContentResolver();
    }

    Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
        ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

    try {
      if (contactLookup != null && contactLookup.getCount() > 0) {
        contactLookup.moveToNext();
        name = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
        //String contactId = contactLookup.getString(contactLookup.getColumnIndex(BaseColumns._ID));
      }
    } finally {
      if (contactLookup != null) {
        contactLookup.close();
      }
    }

    return name;
  }

  public static boolean isNougat() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
  }


  /**
   * convert uri to base64
   * reuse uriToBitmap function
   * update rotate image by exif value
   *
   * @param viewContext: context
   * @param uriStr: uri.toString()
   * @return base 64 or empty string
   */
  public static String uriToBase64(Activity viewContext, String uriStr) {
    Bitmap bitmap = uriToBitmap(viewContext, uriStr);
    if (bitmap == null) {
      return "";
    } else {
      try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        bitmap.recycle();
        final byte[] dataF = baos.toByteArray();
        baos.close();
        return "data:image/jpeg;base64," + Base64.encodeToString(dataF, Base64.NO_WRAP);
      } catch (IOException e) {
        return "";
      }
    }
    /*
    if(viewContext == null) {
      return "";
    }
    else {

      try {
        int orientationImage = getOrientationImage(viewContext,frontImgIdNo);
        InputStream imageStream = viewContext.getContentResolver().openInputStream(Uri.parse(frontImgIdNo));

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(imageStream, null, options);

        imageStream.close();

        InputStream imageStream2 = viewContext.getContentResolver().openInputStream(Uri.parse(frontImgIdNo));

        // Decode bitmap
        int maxImageDimension = Math.max(options.outWidth, options.outHeight);
        options.inSampleSize = Math.max(1, maxImageDimension / 1024);
        options.inJustDecodeBounds = false;
        Bitmap scaledBitmap = BitmapFactory.decodeStream(imageStream2, null, options);
        //xoay lại ảnh như bt (xảy ra đối với thiết bị samsung)
        scaledBitmap = rotateBitmap(scaledBitmap,orientationImage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        scaledBitmap.recycle();

        final byte[] dataF = baos.toByteArray();
        baos.close();

        imageStream2.close();

        return "data:image/jpeg;base64," + Base64.encodeToString(dataF, Base64.DEFAULT);

      }catch (Exception ex)
      {
        Logger.w(ex);
        return "";
      }
    }
     */
  }

  /**
   * compress image to size before convert to base64
   *
   * @param viewContext
   * @param uriStr
   * @param size
   * @return
   */

  public static String uriToBase64(Activity viewContext, String uriStr, int size) {
    Bitmap bitmap = uriToBitmap(viewContext, uriStr);
    if (bitmap == null) {
      return "";
    } else {
      try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (options >= 0 && baos.toByteArray().length > 1024 * size) {
          baos.reset();
          bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
          options -= 10;
        }

        bitmap.recycle();
        final byte[] dataF = baos.toByteArray();
        baos.close();
        return "data:image/jpeg;base64," + Base64.encodeToString(dataF, Base64.DEFAULT);
      } catch (IOException e) {
        return "";
      }
    }
  }

  /**
   * convert uri to bitmap
   *
   * @param viewContext: context
   * @param uriStr:      uri.toString()
   * @return bitmap or null
   */
  public static Bitmap uriToBitmap(Activity viewContext, String uriStr) {
    if (viewContext == null) {
      return null;
    } else {
      try {

        // First decode with inJustDecodeBounds=true to check dimensions
        InputStream inputStreamCheckDimens = viewContext.getContentResolver().openInputStream(Uri.parse(uriStr));
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStreamCheckDimens, null, options);
        if (inputStreamCheckDimens != null) {
          inputStreamCheckDimens.close();
        }

        // Decode bitmap
        InputStream imageStreamDecodeBitmap = viewContext.getContentResolver().openInputStream(Uri.parse(uriStr));
        int maxImageDimension = Math.max(options.outWidth, options.outHeight);
        options.inSampleSize = Math.max(1, maxImageDimension / 1024);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeStream(imageStreamDecodeBitmap, null, options);

        // rotate image by exif
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
          InputStream imageStreamRotate = viewContext.getContentResolver().openInputStream(Uri.parse(uriStr));
          ExifInterface exif = new ExifInterface(imageStreamRotate);
          int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
          bitmap = rotateBitmap(bitmap, orientation);
          if (imageStreamRotate != null) {
            imageStreamRotate.close();
          }
        }

        if (imageStreamDecodeBitmap != null) {
          imageStreamDecodeBitmap.close();
        }
        return bitmap;
      } catch (Exception ex) {
        Logger.w(ex);
        return null;
      }
    }
  }

  /**
   * lấy thông tin orientation từ exif image
   * @param viewContext - context
   * @param uri - đường link uri của ảnh
   * @return orientation image
   */
  public static int getOrientationImage(Activity viewContext, String uri) {
    int orientation = -999;
    InputStream inputStream = null;
    try {
      inputStream = viewContext.getContentResolver().openInputStream(Uri.parse(uri));
      ExifInterface exif = null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        exif = new ExifInterface(inputStream);
        orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return orientation;
  }

  /**
   * lấy thông tin orientation từ exif image
   * @param viewContext - context
   * @param uri - uri của ảnh
   * @return orientation image
   */
  public static int getOrientationImage(Activity viewContext, Uri uri) {
    int orientation = -999;
    InputStream inputStream = null;
    try {
      inputStream = viewContext.getContentResolver().openInputStream(uri);
      ExifInterface exif = null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        exif = new ExifInterface(inputStream);
        orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return orientation;
  }

  /**
   * xoay ảnh tương ứng với orientation
   * @param bitmap - bitmap ảnh
   * @param orientation - orientation của ảnh
   * @return bitmap ảnh đã xoay
   */
  public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

    Matrix matrix = new Matrix();
    switch (orientation) {
      case ExifInterface.ORIENTATION_NORMAL:
        return bitmap;
      case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
        matrix.setScale(-1, 1);
        break;
      case ExifInterface.ORIENTATION_ROTATE_180:
        matrix.setRotate(180);
        break;
      case ExifInterface.ORIENTATION_FLIP_VERTICAL:
        matrix.setRotate(180);
        matrix.postScale(-1, 1);
        break;
      case ExifInterface.ORIENTATION_TRANSPOSE:
        matrix.setRotate(90);
        matrix.postScale(-1, 1);
        break;
      case ExifInterface.ORIENTATION_ROTATE_90:
        matrix.setRotate(90);
        break;
      case ExifInterface.ORIENTATION_TRANSVERSE:
        matrix.setRotate(-90);
        matrix.postScale(-1, 1);
        break;
      case ExifInterface.ORIENTATION_ROTATE_270:
        matrix.setRotate(-90);
        break;
      default:
        return bitmap;
    }
    try {
      Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
      bitmap.recycle();
      return bmRotated;
    }
    catch (OutOfMemoryError e) {
      e.printStackTrace();
      return bitmap;
    }
  }

  //hàm resize kích thước ảnh nếu về base64 400x400
  public static String uriToBase64ForVerify(Activity viewContext, String uriImage) {
    if (viewContext == null) {
      return "";
    } else {

      try {
        int orientationImage = getOrientationImage(viewContext,uriImage);
        InputStream imageStream = viewContext.getContentResolver().openInputStream(Uri.parse(uriImage));

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(imageStream, null, options);

        imageStream.close();

        InputStream imageStream2 = viewContext.getContentResolver().openInputStream(Uri.parse(uriImage));

        // Decode bitmap
//        int maxImageDimension = Math.max(options.outWidth, options.outHeight);
        options.inSampleSize = calculateInSampleSize(options,400,400);
        options.inJustDecodeBounds = false;
        Bitmap scaledBitmap = BitmapFactory.decodeStream(imageStream2, null, options);
        //xoay lại ảnh như bt (xảy ra đối với thiết bị samsung)
        scaledBitmap = scaleBitmap(scaledBitmap,400,400);
        scaledBitmap = rotateBitmap(scaledBitmap,orientationImage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        scaledBitmap.recycle();

        final byte[] dataF = baos.toByteArray();
        baos.close();

        imageStream2.close();

        return "data:image/jpeg;base64," + Base64.encodeToString(dataF, Base64.DEFAULT);

      } catch (Exception ex) {
        Logger.w(ex);
        return "";
      }
    }
  }
  public static int calculateInSampleSize(
          BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

      final int halfHeight = height / 2;
      final int halfWidth = width / 2;

      // Calculate the largest inSampleSize value that is a power of 2 and keeps both
      // height and width larger than the requested height and width.
      while ((halfHeight / inSampleSize) > reqHeight
              && (halfWidth / inSampleSize) > reqWidth) {
        inSampleSize *= 2;
      }
    }

    return inSampleSize;
  }
  private static Bitmap scaleBitmap(Bitmap bm,int maxWidth,int maxHeight) {
    int width = bm.getWidth();
    int height = bm.getHeight();

    Log.v("Pictures", "Width and height are " + width + "--" + height);

    if (width > height) {
      // landscape
      float ratio = (float) width / maxWidth;
      width = maxWidth;
      height = (int)(height / ratio);
    } else if (height > width) {
      // portrait
      float ratio = (float) height / maxHeight;
      height = maxHeight;
      width = (int)(width / ratio);
    } else {
      // square
      height = maxHeight;
      width = maxWidth;
    }

    Log.v("Pictures", "after scaling Width and height are " + width + "--" + height);

    bm = Bitmap.createScaledBitmap(bm, width, height, true);
    return bm;
  }
  public static String convertSignatureBitmapToBase64(Activity activity, Bitmap bitmapImg) {

      final int maxOriginalBitmapDimension = Math.max(bitmapImg.getWidth(), bitmapImg.getHeight());
      boolean needScale;

      Bitmap processedBitmap;

      if (maxOriginalBitmapDimension <= 1024) {

          processedBitmap = bitmapImg;
          needScale = false;

      } else {

          int newImageWidth;
          int newImageHeight;

          if (bitmapImg.getWidth() == maxOriginalBitmapDimension) {

              newImageWidth = 1024;
              newImageHeight = (int) (1024 * bitmapImg.getHeight() * 1.0f / bitmapImg.getWidth());

          } else {

              newImageHeight = 1024;
              newImageWidth = (int) (1024 * bitmapImg.getWidth() * 1.0f / bitmapImg.getHeight());
          }

          processedBitmap = Bitmap.createScaledBitmap(bitmapImg, newImageWidth, newImageHeight, true);
          needScale = true;
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      processedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);

      if (needScale) {
          processedBitmap.recycle();
      }

      final byte[] dataF = baos.toByteArray();

      try {
          baos.close();
      } catch (IOException e) {
          Logger.w(e);
      }

      return "data:image/jpeg;base64," + Base64.encodeToString(dataF, Base64.DEFAULT);
  }

  public static String bitmapToBase64(Activity activity, Bitmap bitmapImg){
    if(activity == null) {
      return "";
    }
    else {
      try {
        Bitmap selectedImage = bitmapImg;
        for (int quality = 80; quality >= 10; quality -= 10) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          selectedImage.compress(Bitmap.CompressFormat.JPEG, quality, baos);
          final byte[] dataF = baos.toByteArray();
          if (dataF.length <= 500000) {
            return "data:image/jpeg;base64," + Base64.encodeToString(dataF, Base64.DEFAULT);
          }
        }

        return null;
      }catch (Exception ex)
      {
        Logger.w(ex);
        return "";
      }
    }
  }

}
