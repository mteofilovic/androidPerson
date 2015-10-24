package com.example.mladenteofilovic.w23d3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private Button mAddButton;
    private EditText mNameEditText;
    private EditText mSurnameEditText;
    public  Persons persons = new Persons();
    private RecyclerView recView;
    private Adapt adapter;
    private Button mEditButton;
    private RadioButton sortByName;
    private RadioButton sortByLastName;

    public static final String EXTRA_DATA_ID = "name_extra";
    public static final String EXTRA_SURNAME_ID = "surname_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = (Button) findViewById(R.id.add_button);
        mNameEditText = (EditText) findViewById(R.id.editText_name);
        mSurnameEditText = (EditText) findViewById(R.id.editText2_surname);
        recView = (RecyclerView) findViewById(R.id.recycler);
        sortByName = (RadioButton) findViewById(R.id.radioButton);
        sortByLastName  =(RadioButton) findViewById(R.id.radioButton2);


        adapter = new Adapt(persons);
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                String surname = mSurnameEditText.getText().toString();

                persons.addPerson(name, surname);
                adapter.notifyDataSetChanged();

            }
        });

        sortByName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sortByName.setChecked(false);
                    Collections.sort(Persons.getPersons().getPersonsList(), new Comparator<Person>() {
                        @Override
                        public int compare(Person lhs, Person rhs) {
                            return lhs.getName().compareTo(rhs.getName());
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
            }
        });

        sortByLastName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sortByLastName.setChecked(false);
                    Collections.sort(Persons.getPersons().getPersonsList(), new Comparator<Person>() {
                        @Override
                        public int compare(Person lhs, Person rhs) {
                            return lhs.getSurname().compareTo(rhs.getSurname());
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    public class PersonView extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView surnameTextView;
        private TextView dateTextView;
        private Button deleteButton;
        private Person person;
        private Button editButton;

        public PersonView(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            surnameTextView = (TextView) itemView.findViewById(R.id.surname_text_view);
            dateTextView = (TextView) itemView.findViewById(R.id.date_text_view);
            deleteButton = (Button) itemView.findViewById(R.id.delete_button);
            editButton = (Button) itemView.findViewById(R.id.edit_button_id);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    persons.removePerson(person.getNumber());
                    updateUI();
                }
            });

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, EditPerson.class);
                    intent.putExtra(EXTRA_DATA_ID, nameTextView.getText().toString());
                    intent.putExtra(EXTRA_SURNAME_ID, surnameTextView.getText().toString());
                    startActivity(intent);
                }
            });

        }
    }

    public void updateUI(){
        Persons personsListing = persons;
        adapter = new Adapt(personsListing);
        recView.setAdapter(adapter);
    }

    public class Adapt extends RecyclerView.Adapter<PersonView> {

        private Persons persons;

        public Adapt(Persons persons) {
            this.persons = persons;
        }

        @Override
        public PersonView onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

            View view = layoutInflater.inflate(R.layout.person_view, parent, false);

            return new PersonView(view);
        }

        @Override
        public void onBindViewHolder(PersonView holder, int position) {
            Person person = persons.getPerson(position);

            holder.nameTextView.setText(person.getName());
            holder.surnameTextView.setText(person.getSurname());
            holder.dateTextView.setText(person.getNewDate().toString());
            holder.person = person;
        }

        @Override
        public int getItemCount() {
            return persons.getSize();
        }
    }

}
