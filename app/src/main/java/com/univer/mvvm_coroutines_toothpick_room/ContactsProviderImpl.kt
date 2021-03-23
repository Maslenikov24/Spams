package com.univer.mvvm_coroutines_toothpick_room

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CallLog
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.toDate
import com.univer.mvvm_coroutines_toothpick_room.data.domain.contact.Contact

class ContactsProviderImpl(
    private val context: Context
) : ContactsProvider {

    companion object{
        const val CALLS_ID = CallLog.Calls._ID
        const val CACHED_PHOTO_URI = CallLog.Calls.CACHED_PHOTO_URI
        const val CACHED_FORMATTED_NUMBER = CallLog.Calls.CACHED_FORMATTED_NUMBER
        const val DATE = CallLog.Calls.DATE
        const val CACHED_NAME = CallLog.Calls.CACHED_NAME
        const val DURATION = CallLog.Calls.DURATION
        const val TYPE = CallLog.Calls.TYPE
        val CALLS_CONTENT_URI: Uri = CallLog.Calls.CONTENT_URI

        private fun closeCursorIfExists(vararg cursor: Cursor?) {
            cursor.forEach {
                if (it?.isClosed == false) {
                    it.close()
                }
            }
        }

        private fun Cursor.getString(param: String) = this.getString(this.getColumnIndex(param))

        private fun Cursor.getInt(param: String) = this.getInt(this.getColumnIndex(param))

        private fun Cursor.getLong(param: String) = this.getLong(this.getColumnIndex(param))
    }

    private val callLogs = arrayOf(
        CALLS_ID,
        DATE,
        CACHED_FORMATTED_NUMBER,
        CACHED_PHOTO_URI,
        CACHED_NAME,
        DURATION,
        TYPE
    )

    @SuppressLint("MissingPermission")
    private val cursorCalls = context.contentResolver.query(
        CALLS_CONTENT_URI,
        callLogs,
        "",
        null,
        null
    )

    private val calls = mutableListOf<Contact.ContactInfo>()
    private val wrappedCalls = mutableListOf<Contact>()

    private fun getCalls() {
        if (cursorCalls?.moveToFirst() == true) {
            do {
                val number = cursorCalls.getString(CACHED_FORMATTED_NUMBER)
                val type = cursorCalls.getInt(TYPE)
                if (calls.size > 0) {
                    if (number == calls.last().number && type == calls.last().type) {
                        calls.last().count++
                        continue
                    }
                }
                val id = cursorCalls.getLong(CALLS_ID)
                val name = cursorCalls.getString(CACHED_NAME)
                val date = cursorCalls.getLong(DATE)

                number?.let { calls.add(Contact.ContactInfo(id, number, name, null, type, date)) }

            } while (cursorCalls.moveToNext())
        }
    }

    private fun wrapCalls(data: List<Contact.ContactInfo>){
        var date = 0L

        data.forEach {
            if (it.date.toDate().substring(0,10) != date.toDate().substring(0,10)) {
                wrappedCalls.add(Contact.ContactDate(date))
                date = it.date
            }
            wrappedCalls.add(it)
        }
    }

    override fun getPhoneBook(): List<Contact> {
        getCalls()
        wrapCalls(calls.sortedByDescending { it.date })
        closeCursorIfExists(cursorCalls)
        return wrappedCalls.toList()
    }
}