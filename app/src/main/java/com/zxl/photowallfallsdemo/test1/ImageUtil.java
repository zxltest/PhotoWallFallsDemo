package com.zxl.photowallfallsdemo.test1;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片处理
 * 
 * @author maylian.mei
 * 
 */
public class ImageUtil
{
	private static final String tag = "ImageUtil";

	/**
	 * 缩放图片
	 */
	public static Drawable resizeImage(Bitmap bitmap, int w, int h)
	{

		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		Log.v(tag, String.valueOf(width));
		Log.v(tag, String.valueOf(height));

		Log.v(tag, String.valueOf(newWidth));
		Log.v(tag, String.valueOf(newHeight));

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);

		// make a Drawable from Bitmap to allow to set the Bitmap
		// to the ImageView, ImageButton or what ever
		return new BitmapDrawable(resizedBitmap);

	}
	
	public static Drawable cropImage(Bitmap bitmap, int w, int h)
	{
		
		return null;
	}
	/** 
	 * 读取图片属：旋转的角度
	 */
   public static int readPictureDegree(String path) {  
       int degree  = 0;  
       try {  
               ExifInterface exifInterface = new ExifInterface(path);  
               int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);  
               switch (orientation) {  
               case ExifInterface.ORIENTATION_ROTATE_90:  
                       degree = 90;  
                       break;  
               case ExifInterface.ORIENTATION_ROTATE_180:  
                       degree = 180;  
                       break;  
               case ExifInterface.ORIENTATION_ROTATE_270:  
                       degree = 270;  
                       break;  
               }  
       } catch (IOException e) {  
               e.printStackTrace();  
       }  
       return degree;  
   }  
   /** 
    * 旋转图片 
    */
   public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {  
       //旋转图片 动作  
       Matrix matrix = new Matrix();;  
       matrix.postRotate(angle);  
       System.out.println("angle2=" + angle);  
       // 创建新的图片  
       Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,  
               bitmap.getWidth(), bitmap.getHeight(), matrix, true);  
       return resizedBitmap;  
   }
   /**
    * 将图片保存到本地
    */
   public static String saveToLocal(Bitmap bm , String path){
   	try{
		FileOutputStream fos = new FileOutputStream(path);
		bm.compress(CompressFormat.JPEG, 75, fos);
		fos.flush();
		fos.close();
	} catch (FileNotFoundException e)
	{
		e.printStackTrace();
		return null;
	} catch (IOException e)
	{
		e.printStackTrace();
		return null;
	}
	
	return path;
   }
   /**
    * 在线请求大图
    */
   public static Bitmap loadImageFromUrl(String url, int sc) {
       URL m;
       InputStream i = null;
       BufferedInputStream bis = null;
       ByteArrayOutputStream out = null;
       byte isBuffer[] = new byte[1024];
       if (url == null)
           return null;
       try {
           m = new URL(url);
           i = (InputStream) m.getContent();

           bis = new BufferedInputStream(i, 1024 * 4);
           out = new ByteArrayOutputStream();
           int len = 0;
           while ((len = bis.read(isBuffer)) != -1) {
               out.write(isBuffer, 0, len);
           }
           out.close();
           bis.close();
       } catch (MalformedURLException e1) {
           e1.printStackTrace();
           return null;
       } catch (IOException e) {
           e.printStackTrace();
       }
       if (out == null)
           return null;
       byte[] data = out.toByteArray();
       BitmapFactory.Options options = new BitmapFactory.Options();
       options.inJustDecodeBounds = true;
       BitmapFactory.decodeByteArray(data, 0, data.length, options);
       options.inJustDecodeBounds = false;
       int be = (int) (options.outHeight / (float) sc);
       if (be <= 0) {
           be = 1;
       } else if (be > 3) {
           be = 3;
       }
       options.inSampleSize = be;
       Bitmap bmp = null;
       try {
           bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options); // 返回缩略�?
       } catch (OutOfMemoryError e) {
           // TODO: handle exception
           System.gc();
           bmp.recycle();
           bmp = null;
       }
       return bmp;
   }
}
