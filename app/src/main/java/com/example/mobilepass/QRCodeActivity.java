package com.example.mobilepass;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
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
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(com.google.android.gms.base.R.color.common_google_signin_btn_text_dark) : getResources().getColor(com.google.android.gms.base.R.color.common_google_signin_btn_text_light);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}