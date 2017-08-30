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

    fun switchEditMode(){
        viewModel.editMode = !viewModel.editMode
    }

    fun contactPicked(data: Intent, context:Context) {
        var cursor: Cursor? = null
        try {
            var phoneNo: String? = null
            var name: String? = null
            val uri = data.data
            cursor = context.contentResolver.query(uri!!, null, null, null, null)
            cursor!!.moveToFirst()
            val phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            phoneNo = cursor.getString(phoneIndex)
            name = cursor.getString(nameIndex)
            cursor.close()

            viewModel.name = name
            viewModel.number = phoneNo

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}