package rrozanski.wisiszmihajs

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.debt_main_item.view.*
import rrozanski.wisiszmihajs.databinding.DebtMainItemBinding

/**
 * Created by robert on 28.08.2017.
 */


class MainAdapter(internal var vmList: ArrayList<DebtPresenter>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun addToAdapter(presenter: DebtPresenter) {
        vmList.add(presenter)
        notifyItemInserted(vmList.size)
    }

    fun removeFromAdapter(pos: Int) {
        vmList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val binding = DataBindingUtil.inflate<DebtMainItemBinding>(
                LayoutInflater.from(parent.context), R.layout.debt_main_item, parent, false)
        return DebtViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        val viewHolder0 = holder as DebtViewHolder
        val binding = viewHolder0.binding
        binding.presenter = vmList[pos]
        binding.viewModel = vmList[pos].viewModel

        binding.root.settings_icon.setOnClickListener({
            binding.presenter.switchEditMode()
            notifyItemChanged(holder.adapterPosition)
        })

        binding.root.save_icon.setOnClickListener({
            binding.presenter.switchEditMode()
            notifyItemChanged(holder.adapterPosition)
        })

        binding.root.delete_icon.setOnClickListener({
            removeFromAdapter(holder.adapterPosition)
        })
    }

    override fun getItemCount(): Int {
        return vmList.size
    }

    inner class DebtViewHolder(val binding: DebtMainItemBinding) : RecyclerView.ViewHolder(binding.root)

}
