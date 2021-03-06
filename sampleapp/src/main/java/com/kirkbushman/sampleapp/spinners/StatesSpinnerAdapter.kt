package com.kirkbushman.sampleapp.spinners

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.databinding.SpinnerStateItemBinding
import com.kirkbushman.zammad.models.TicketState

class StatesSpinnerAdapter(

    context: Context,
    textViewResourceId: Int,

    private val objects: List<TicketState>

) : ArrayAdapter<TicketState>(context, textViewResourceId, objects) {

    private val inflater = LayoutInflater.from(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = convertView ?: inflater.inflate(R.layout.spinner_state_item, parent, false)
        val binding = SpinnerStateItemBinding.bind(layout)

        binding.spinnerText.text = objects[position].name

        return layout
    }
}
