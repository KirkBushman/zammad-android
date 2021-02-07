package com.kirkbushman.sampleapp.spinners

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.databinding.SpinnerUserItemBinding
import com.kirkbushman.zammad.models.User

class UserSpinnerAdapter(

    context: Context,
    textViewResourceId: Int,

    private val objects: List<User?>

) : ArrayAdapter<User>(context, textViewResourceId, objects) {

    private val inflater = LayoutInflater.from(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layout = convertView ?: inflater.inflate(R.layout.spinner_user_item, parent, false)
        val binding = SpinnerUserItemBinding.bind(layout)

        val item = objects[position]

        val text = item?.firstname?.plus(if (item.email != "") " (" + item.email + ")" else "") ?: ""
        binding.spinnerText.text = text

        return layout
    }
}
