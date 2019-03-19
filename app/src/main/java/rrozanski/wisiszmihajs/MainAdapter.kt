package rrozanski.wisiszmihajs

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.debt_main_item.view.*
import rrozanski.wisiszmihajs.databinding.DebtMainItemBinding
import rx.functions.Action0
import rx.functions.Action1


/**
 * Created by robert on 28.08.2017.
 */

class MainAdapter(
    internal var vmList: ArrayList<DebtPresenter>,
    internal val permissionsAction: Action0,
    internal val openContactsAction: Action1<Int>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            LayoutInflater.from(parent.context), R.layout.debt_main_item, parent, false
        )
        return DebtViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        val viewHolder0 = holder as DebtViewHolder
        val binding = viewHolder0.binding
        binding.presenter = vmList[pos]
        binding.viewModel = vmList[pos].viewModel

        val context: Context = binding.root.context

        binding.root.debtor_edittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (Math.abs(count - before) == 1) {
                    binding.viewModel?.number?.set("")
                }
            }
        })

        binding.root.collapse_button.setOnClickListener {
            binding.viewModel?.collapsed = !binding.viewModel?.collapsed!!
            notifyItemChanged(holder.adapterPosition)
        }

        binding.root.collapsed_button.setOnClickListener {
            binding.viewModel?.collapsed = !binding.viewModel?.collapsed!!
            notifyItemChanged(holder.adapterPosition)
        }

        binding.root.settings_icon.setOnLongClickListener {
            binding.presenter?.switchEditMode()
            notifyItemChanged(holder.adapterPosition)
            true
        }

        binding.root.contact_book.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_CONTACTS
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsAction.call()
            } else {
                openContactsAction.call(holder.adapterPosition)
            }
        }

        binding.root.save_icon.setOnClickListener {
            binding.presenter?.switchEditMode()
            notifyItemChanged(holder.adapterPosition)
        }

        binding.root.delete_icon.setOnLongClickListener {
            removeFromAdapter(holder.adapterPosition)
            //todo popup
            true
        }

        binding.root.sms_icon.setOnLongClickListener {
            Toast.makeText(binding.root.context, "Jeszcze nie stworzono", Toast.LENGTH_SHORT).show()
            //todo
            true
        }
    }

    override fun getItemCount(): Int {
        return vmList.size
    }

    class DebtViewHolder(val binding: DebtMainItemBinding) : RecyclerView.ViewHolder(binding.root)
}
