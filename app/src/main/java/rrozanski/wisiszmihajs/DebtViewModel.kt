package rrozanski.wisiszmihajs

/**
 * Created by robert on 28.08.2017.
 */

data class DebtViewModel(
    var name: String = "",
    var number: String = "",
    var debt: String = "",
    var notes: String = "",
    var date: String = "",
    var editMode: Boolean
)
