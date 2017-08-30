package rrozanski.wisiszmihajs

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import rrozanski.wisiszmihajs.databinding.ActivityMainBinding
import rrozanski.wisiszmihajs.di.DI

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.di().injectors().inject(this)

//        var onePresenter: DebtPresenter = DebtPresenter()
//        onePresenter.viewModel = DebtViewModel("Tomasz N", "20zł i trzy warki",
//                "notatka", "28.08.2017", false)
//        var twoPresenter: DebtPresenter = DebtPresenter()
//        twoPresenter.viewModel = DebtViewModel("Paweł Ch", "90zł i cztery warki",
//                "notatka2", "29.08.2017", false)
        val vmList: ArrayList<DebtPresenter> = ArrayList()

//        vmList.add(onePresenter)
//        vmList.add(twoPresenter)

        val mainAdapter: MainAdapter = MainAdapter(vmList)
        val mainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.mainRecycler.adapter = mainAdapter
        main_recycler.layoutManager = LinearLayoutManager(this)

        new_debt.setOnClickListener({
            val newPresenter: DebtPresenter = DebtPresenter()
            newPresenter.viewModel = DebtViewModel(editMode = true)
            mainAdapter.addToAdapter(newPresenter)
        })

    }

}
