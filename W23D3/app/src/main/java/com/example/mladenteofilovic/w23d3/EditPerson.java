package com.example.mladenteofilovic.w23d3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditPerson extends AppCompatActivity {

    private Button updatePersonButton;
    private EditText nameEditText;
    private EditText surnameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        updatePersonButton = (Button) findViewById(R.id.update_button);
        nameEditText = (EditText) findViewById(R.id.name_edit_text_editView);
        surnameEditText = (EditText) findViewById(R.id.surname_edit_view);

        String name = getIntent().getExtras().get(MainActivity.EXTRA_DATA_ID).toString();
        String surname = getIntent().getExtras().getString(MainActivity.EXTRA_SURNAME_ID);

        nameEditText.setText(name);
        surnameEditText.setText(surname);


        updatePersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
