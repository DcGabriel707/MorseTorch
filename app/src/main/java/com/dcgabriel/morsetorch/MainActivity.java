package com.dcgabriel.morsetorch;

import android.Manifest;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView outputTextView;
    private EditText messageEditText;
    private String finalMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputTextView = findViewById(R.id.output_message_textView);
        messageEditText = findViewById(R.id.input_message_editText);
    }

    public void convertButton(View view) {
        String message = messageEditText.getText().toString().toLowerCase();

        char charMessage[] = message.toCharArray();
        String morseMessage = "";
        for (int i = 0; i < charMessage.length; i++) {
            morseMessage = morseMessage + toMorse(charMessage[i]);
        }

        finalMessage = morseMessage;
        outputTextView.setText(finalMessage);

    }

    private String toMorse(char character) {
        switch (character) {
            case 'a':
                return ".-";
            case 'b':
                return "-...";

            case 'c':
                return "-.-.";
            case 'd':
                return "-..";
            case 'e':
                return ".";
            case 'f':
                return "..-.";
            case 'g':
                return "--.";
            case 'h':
                return "....";
            case 'i':
                return "..";
            case 'j':
                return ".---";
            case 'k':
                return "-.-";
            case 'l':
                return ".-..";
            case 'm':
                return "--";
            case 'n':
                return "-.";
            case 'o':
                return "---";
            case 'p':
                return ".--.";
            case 'q':
                return "--.-";
            case 'r':
                return ".-.";
            case 's':
                return "...";
            case 't':
                return "-";
            case 'u':
                return "..-";
            case 'v':
                return "...-";
            case 'w':
                return ".--";
            case 'x':
                return "-..-";
            case 'y':
                return "-.--";
            case 'z':
                return "--..";
            default:
                return " ";
        }


    }

    public void transmitButton(View view) {
        convertButton(view);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
        for (int i = 0; i < finalMessage.length(); i++) {
            char c = finalMessage.charAt(i);
            if (c == '.') {
                dot();
            } else if (c == '-')
                dash();
            else
                space();

        }


    }

    public void dot() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            Thread.sleep(340);
            cameraManager.setTorchMode(cameraId, false);
            Thread.sleep(200);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dash() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            Thread.sleep(600);
            cameraManager.setTorchMode(cameraId, false);
            Thread.sleep(200);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void space() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void clearButton(View view) {
        outputTextView.setText("");
        messageEditText.getText().clear();
    }

}
