package es.pue.intentspractice.presentationlayer.controllers.activities

import android.app.Activity
import android.content.Intent
import android.database.Cursor
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
        contacts_lvContacts.adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_1,
            queryForContacts(),
            arrayOf(ContactsContract.Contacts.DISPLAY_NAME),
            intArrayOf(android.R.id.text1),
            1
        )

        contacts_lvContacts.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, id -> returnToDialer(id) }
    }

    private fun queryForContacts(): Cursor? {
        return contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            ContactsContract.Contacts.HAS_PHONE_NUMBER + "=?",
            arrayOf("1"),
            ContactsContract.Contacts.DISPLAY_NAME + " Desc"
        )
    }

    private fun returnToDialer(id: Long) {
        val cursor = queryForContactWithId(id)
        val phoneNumbers = buildPhoneNumbersList(cursor!!)

        cursor.close()

        if (phoneNumbers.size > 0) {
            val intent = Intent()
            intent.putExtra("telefono", phoneNumbers[0])
            setResult(Activity.RESULT_OK, intent)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }

        finish()
    }

    private fun queryForContactWithId(id: Long): Cursor? {
        return contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
            arrayOf(id.toString())
            ,
            null
        )
    }

    private fun buildPhoneNumbersList(cursor: Cursor): ArrayList<String> {
        val phoneNumbers = ArrayList<String>()

        while (cursor.moveToNext()) {
            phoneNumbers.add(
                cursor.getString(
                    cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )
            )
        }

        return phoneNumbers
    }
}
