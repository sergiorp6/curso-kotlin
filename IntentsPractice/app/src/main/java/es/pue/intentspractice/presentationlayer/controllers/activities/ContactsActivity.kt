package es.pue.intentspractice.presentationlayer.controllers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.AdapterView
import android.widget.SimpleCursorAdapter
import es.pue.intentspractice.R
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        loadContacts()
    }

    private fun loadContacts() {
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            ContactsContract.Contacts.HAS_PHONE_NUMBER + "=?",
            arrayOf("1"),
            ContactsContract.Contacts.DISPLAY_NAME + " Desc"
        )

        contacts_lvContacts.adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cursor,
            arrayOf(ContactsContract.Contacts.DISPLAY_NAME),
            intArrayOf(android.R.id.text1),
            1
        )

        contacts_lvContacts.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, id -> returnToDialer(id) }
    }

    private fun returnToDialer(id: Long) {
        id +1
    }
}
