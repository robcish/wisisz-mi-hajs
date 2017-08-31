package rrozanski.wisiszmihajs

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.provider.ContactsContract

/**
 * Created by robert on 29.08.2017.
 */
class DebtPresenter{
    lateinit var viewModel: DebtViewModel

    fun switchEditMode() {
        viewModel.editMode = !viewModel.editMode
    }

    fun contactPicked(data: Intent, context: Context) {
        val cursor: Cursor?
        try {
            val uri = data.data
            cursor = context.contentResolver.query(uri!!, null, null, null, null)
            cursor!!.moveToFirst()
            val phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            viewModel.name.set(cursor.getString(nameIndex))
            viewModel.number.set(cursor.getString(phoneIndex))
            viewModel.contactFromBook = true
            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}