package com.tinuade.imeoptions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.phoneEdit);
        if (editText!=null){
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                   boolean handled=false;
                   if (actionId== EditorInfo.IME_ACTION_SEND){
                       dialNumber();
                       handled=true;
                       
                   }
                    return handled;
                }
            });
        }
    }

    private void dialNumber() {
        String phoneNumber=null;
        // If the editText field is not null,
        // concatenate "tel: " with the phone number string.
        if (editText!=null)phoneNumber="tel:"+editText.getText().toString();
        // Specify the intent.
        Intent intent= new Intent(Intent.ACTION_DIAL);
        // Set the data for the intent as the phone number.
        intent.setData(Uri.parse(phoneNumber));
        // If the intent resolves to a package (app),
        // start the activity with the intent.
        if (intent.resolveActivity(getPackageManager())!=null){
                startActivity(intent);
        }else {
            Toast.makeText(this,"Can't handle this",Toast.LENGTH_SHORT).show();
        }

    }
}
