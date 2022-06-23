package com.example.mobilepass;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRCodeActivity extends Activity {
    public final static int QRcodeWidth = 500;
    Bitmap bitmap;
    private EditText etqr;
    private ImageView iv;
    private Button btn;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        iv = (ImageView) findViewById(R.id.iv);
        btn = (Button) findViewById(R.id.btn);
        etqr = (EditText) findViewById(R.id.etqr);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etqr.getText().toString().trim().length() == 0) {
                    Toast.makeText(QRCodeActivity.this, "Enter String!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        bitmap = TextToImageEncode(etqr.getText().toString());
                        iv.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private Bitmap TextToImageEncode(String Value) throws WriterException {
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = new MultiFormatWriter().encode(
//                    Value,
//                    BarcodeFormat.QR_CODE,
//                    QRcodeWidth, 400, null
//            );
//
//        } catch (IllegalArgumentException Illegalargumentexception) {
//
//            return null;
//        }
//        int bitMatrixWidth = bitMatrix.getWidth();
//
//        int bitMatrixHeight = bitMatrix.getHeight();
//
//        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
//
//        for (int y = 0; y < bitMatrixHeight; y++) {
//            int offset = y * bitMatrixWidth;
//
//            for (int x = 0; x < bitMatrixWidth; x++) {
//
//                pixels[offset + x] = bitMatrix.get(x, y) ?
//                        getResources().getColor(com.google.android.gms.base.R.color.common_google_signin_btn_text_dark) : getResources().getColor(com.google.android.gms.base.R.color.common_google_signin_btn_text_light);
//            }
//        }
//        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
//
//        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
//        return bitmap;
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder(etqr.getText().toString(), null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.

        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }
        return bitmap;
    } }
