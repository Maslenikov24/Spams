package com.graduate.spams.model.call.provider.contacts

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import com.graduate.spams.data.contact.domain.Contact
import com.graduate.spams.core.extensions.getString
import com.graduate.spams.core.extensions.getLong

class ContactsProviderImpl(
	private val context: Context
) : ContactsProvider {

	companion object {
		const val CONTACTS_ID = ContactsContract.Contacts._ID
		const val PHONE_ID = ContactsContract.CommonDataKinds.Phone._ID
		const val HAS_PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER
		const val PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
		const val PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER
		const val PHONE_DISPLAY_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
		val CONTACT_CONTENT_URI: Uri = ContactsContract.Contacts.CONTENT_URI
		val PHONE_CONTENT_URI: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
	}

	private val cursorContacts = context.contentResolver.query(
		CONTACT_CONTENT_URI,
		null,
		null,
		null,
		null
	)
	private val contacts = mutableListOf<Contact.ContactInfo>()

	override fun getContacts() : List<Contact.ContactInfo> {
		if (cursorContacts?.moveToFirst() == true) {
			do {
				val numbers = mutableListOf<String>()
				val contactId = cursorContacts.getString(CONTACTS_ID)
				val id = cursorContacts.getLong(PHONE_ID)
				if (cursorContacts.getString(HAS_PHONE_NUMBER).toInt() == 1) {
					val phoneCursor = context.contentResolver.query(
						PHONE_CONTENT_URI,
						null,
						"$PHONE_CONTACT_ID = ?",
						arrayOf(contactId),
						null
					)
					while (phoneCursor?.moveToNext() == true) {
						numbers.add(phoneCursor.getString(PHONE_NUMBER))
					}
					closeCursorIfExists(phoneCursor)
				}
				val name = cursorContacts.getString(PHONE_DISPLAY_NAME)

				numbers.forEach {
					val contact = Contact.ContactInfo(id, it, name)
					if (contacts.lastOrNull()?.number?.normalized() !=
						contact.number.normalized()) contacts.add(contact)
				}
			} while (cursorContacts.moveToNext())
		}
		closeCursorIfExists(cursorContacts)
		return contacts
	}

	private fun closeCursorIfExists(vararg cursor: Cursor?) {
		cursor.forEach {
			if (it?.isClosed == false) {
				it.close()
			}
		}
	}

	private fun String.normalized() : String {
		return this.replace(Regex("""[^\d]+"""), "").substring(1)
	}

	fun checkingNumberExistence(phoneNumber: String): Boolean {
		getContacts()
		val incomingPhoneNumber = phoneNumber.normalized()
		contacts.forEach {
			if (incomingPhoneNumber == it.number.normalized()) return true
		}
		return false
	}
}