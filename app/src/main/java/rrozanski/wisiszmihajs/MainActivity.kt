package rrozanski.wisiszmihajs

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import io.vrinda.kotlinpermissions.PermissionsActivity
import kotlinx.android.synthetic.main.activity_main.*
import rrozanski.wisiszmihajs.databinding.ActivityMainBinding
import rrozanski.wisiszmihajs.di.DI
import rx.functions.Action0
import rx.functions.Action1


class MainActivity : PermissionsActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var pickContactAction: Action1<Int>
    lateinit var recycler: RecyclerView

    val PERMISSION_READ_CONTACTS: Int = 567
    val GET_CONTACT_REQUEST_CODE: Int = 987

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.di().injectors().inject(this)

        val vmList: ArrayList<DebtPresenter> = ArrayList()
        val contactPermissions: Array<String> = arrayOf(Manifest.permission.READ_CONTACTS)

        val permissionsAction: Action0 = Action0 {
            ActivityCompat.requestPermissions(this, contactPermissions, PERMISSION_READ_CONTACTS)
        }

        pickContactAction = Action1<Int> {
            output: Int? ->
            Log.e("abc", output.toString())
            val contactIntent: Intent = Intent(Intent.ACTION_PICK)
            contactIntent.putExtra("pos", output)
            contactIntent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(contactIntent, GET_CONTACT_REQUEST_CODE)
        }

        val mainAdapter: MainAdapter = MainAdapter(vmList, permissionsAction, pickContactAction)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recycler = mainBinding.mainRecycler
        recycler.adapter = mainAdapter
        recycler.layoutManager = LinearLayoutManager(this)

        new_debt.setOnClickListener({
            val newPresenter: DebtPresenter = DebtPresenter()
            newPresenter.viewModel = DebtViewModel(editMode = true)
            mainAdapter.addToAdapter(newPresenter)
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_READ_CONTACTS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(mainBinding.root, "Przyznano pozwolenie", Snackbar.LENGTH_SHORT)
                } else {
                    Snackbar.make(mainBinding.root, "Pozwolenie jest wymagane", Snackbar.LENGTH_SHORT)
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GET_CONTACT_REQUEST_CODE -> {
                    val pos:Int = data.getIntExtra("pos", -1)
                    val viewHolder = recycler.findViewHolderForAdapterPosition(pos) as MainAdapter.DebtViewHolder
                    viewHolder.binding.presenter.contactPicked(data, this)
                    recycler.adapter.notifyItemChanged(pos)
                }
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact")
        }
    }

}
