package rrozanski.wisiszmihajs

import android.databinding.ObservableField

/**
 * Created by robert on 28.08.2017.
 */

data class DebtViewModel(
        var name: ObservableField<String> = ObservableField(""),
        var number: ObservableField<String> = ObservableField(""),
        var debt: ObservableField<String> = ObservableField(""),
        var notes: ObservableField<String> = ObservableField(""),
        var date: ObservableField<String> = ObservableField(""),
        var contactFromBook: Boolean = false,
        var editMode: Boolean,
        var collapsed: Boolean = false)
