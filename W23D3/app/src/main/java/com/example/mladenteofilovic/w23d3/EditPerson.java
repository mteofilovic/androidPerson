package com.example.mladenteofilovic.w23d3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditPerson extends AppCompatActivity {

    private Button updatePersonButton;
    private EditText nameEditText;
    private EditText surnameEditText;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        updatePersonButton = (Button) findViewById(R.id.update_button);
        nameEditText = (EditText) findViewById(R.id.name_edit_text_editView);
        surnameEditText = (EditText) findViewById(R.id.surname_edit_view);

        Person person = (Person) getIntent().getExtras().getSerializable(MainActivity.EXTRA_PERSON);

        nameEditText.setText(person.getName());
        surnameEditText.setText(person.getSurname());


        updatePersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persons.getPersons().getPerson(position).setName(nameEditText.getText().toString());
                Persons.getPersons().getPerson(position).setName(surnameEditText.getText().toString());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
