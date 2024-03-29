package com.example.sharingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Add a new contact
 */
public class AddContactActivity extends AppCompatActivity {

    private ContactListController contactListController;
    private Context context;

    private EditText username;
    private EditText email;

    public void test() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        context = getApplicationContext();

        ContactList contact_list = new ContactList();
        contact_list.loadContacts(context);
        contactListController = new ContactListController(contact_list);
    }

    public void saveContact(View view) {

        String username_str = username.getText().toString();
        String email_str = email.getText().toString();

        if (username_str.equals("")) {
            username.setError("Empty field!");
            return;
        }

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")) {
            email.setError("Must be an email address!");
            return;
        }

        for (Contact c : contactListController.getContacts()) {
            if (c.getUsername().equals(username_str)) {
                username.setError("Username already taken!");
                return;
            }
        }

        Contact contact = new Contact(username_str, email_str, null);

        // Add Contact

        boolean success = contactListController.addContact(contact, context);
        if (!success) {
            return;
        }

        // End AddContactActivity
        finish();
    }
}
