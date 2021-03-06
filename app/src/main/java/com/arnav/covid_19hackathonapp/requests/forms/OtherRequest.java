package com.arnav.covid_19hackathonapp.requests.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arnav.covid_19hackathonapp.R;
import com.arnav.covid_19hackathonapp.RequestCompletion;
import com.arnav.covid_19hackathonapp.requests.firebase.OtherRequestData;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class OtherRequest extends AppCompatActivity {
    TextInputLayout addressreq, suitereq, statereq, namereq, amountreq, commentsreq, cityreq, phonereq, whatreq;
    Button submitreq;

    FirebaseDatabase database;
    DatabaseReference reference, authref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_request);

        //Hooks
        addressreq = findViewById(R.id.street_address_other_request);
        suitereq = findViewById(R.id.suite_other);
        statereq = findViewById(R.id.state_request);
        namereq = findViewById(R.id.name_other);
        amountreq = findViewById(R.id.amount_other_request);
        commentsreq = findViewById(R.id.comments_other_request);
        submitreq = findViewById(R.id.submit_other_request);
        cityreq = findViewById(R.id.city_other_request);
        phonereq = findViewById(R.id.phone_other_request);
        whatreq = findViewById(R.id.what_other);

        submitreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate
                if (!validateAddress() || !validateApartment() || !validateState() || !validateNumber() || !validateComments() || !validateCity() || !validateHospital()) {
                    return;
                }
                database = FirebaseDatabase.getInstance();
                authref = database.getReference("Users");
                reference = database.getReference("OtherRequest");


                String address = addressreq.getEditText().getText().toString();
                String suite = suitereq.getEditText().getText().toString();
                String state = statereq.getEditText().getText().toString();
                String amount = amountreq.getEditText().getText().toString();
                String comments = commentsreq.getEditText().getText().toString();
                String city = cityreq.getEditText().getText().toString();
                String hospital = namereq.getEditText().getText().toString();
                String what = whatreq.getEditText().getText().toString();
                final String phone = phonereq.getEditText().getText().toString();

                final OtherRequestData otherData = new OtherRequestData(address, suite, city, state, hospital, amount, comments, what);
                //add data
                authref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String nameFromDB = dataSnapshot.child(phone).child("name").getValue(String.class);
                            String phoneNumberFromDB = dataSnapshot.child(phone).child("phoneNumber").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(phone).child("email").getValue(String.class);
                            reference.child(phoneNumberFromDB).setValue(otherData);
                            reference.child(phoneNumberFromDB).child("Name").setValue(nameFromDB);
                            reference.child(phoneNumberFromDB).child("Phone_Number").setValue(phoneNumberFromDB);
                            reference.child(phoneNumberFromDB).child("Email").setValue(emailFromDB);

                            Intent intent = new Intent(getApplicationContext(), RequestCompletion.class);
                            intent.putExtra("emailFromDB", emailFromDB);
                            intent.putExtra("nameFromDB", nameFromDB);
                            startActivity(intent);
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    private Boolean validateAddress() {
        String value = addressreq.getEditText().getText().toString();
        if (value.isEmpty()) {
            addressreq.setError("Field Is Required");
            return false;
        } else {
            addressreq.setError(null);
            addressreq.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateApartment() {
        String value = suitereq.getEditText().getText().toString();
        if (value.isEmpty()) {
            suitereq.setError(null);
            suitereq.setSoundEffectsEnabled(false);
            return true;
        } else {
            suitereq.setError(null);
            suitereq.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateState() {
        String value = statereq.getEditText().getText().toString();
        if (value.isEmpty()) {
            statereq.setError("Field Is Required");
            return false;
        } else {
            statereq.setError(null);
            statereq.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateNumber() {
        String value = amountreq.getEditText().getText().toString();
        if (value.isEmpty()) {
            amountreq.setError("Field Is Required");
            return false;
        } else {
            amountreq.setError(null);
            amountreq.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateComments() {
        String value = commentsreq.getEditText().getText().toString();
        if (value.isEmpty()) {
            commentsreq.setError(null);
            commentsreq.setErrorEnabled(false);
            return true;
        } else {
            commentsreq.setError(null);
            commentsreq.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateCity() {
        String value = cityreq.getEditText().getText().toString();
        if (value.isEmpty()) {
            cityreq.setError("Field Is Required");
            return false;
        } else {
            cityreq.setError(null);
            cityreq.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validateHospital() {
        String value = namereq.getEditText().getText().toString();
        if (value.isEmpty()) {
            namereq.setError("Field Is Required");
            return false;
        } else {
            namereq.setError(null);
            namereq.setErrorEnabled(false);
            return true;
        }
    }


}
