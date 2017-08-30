package rrozanski.wisiszmihajs

/**
 * Created by robert on 29.08.2017.
 */
class DebtPresenter{
    lateinit var viewModel: DebtViewModel

    fun switchEditMode(){
        viewModel.editMode = !viewModel.editMode
    }
}