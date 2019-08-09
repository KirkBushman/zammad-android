package com.kirkbushman.sampleapp.spinners

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.Group
import kotlinx.android.synthetic.main.spinner_group_item.view.*

class GroupSpinnerAdapter(

    context: Context,
    textViewResourceId: Int,

    private val objects: List<Group>

) : ArrayAdapter<Group>(context, textViewResourceId, objects) {

    private val inflater = LayoutInflater.from(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layout = convertView ?: inflater.inflate(R.layout.spinner_group_item, parent, false)
        layout.spinner_text.text = objects[position].name

        return layout
    }
}
