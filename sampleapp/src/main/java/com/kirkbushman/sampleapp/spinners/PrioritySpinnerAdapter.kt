package com.kirkbushman.sampleapp.spinners

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.ColorInt
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.databinding.SpinnerPriorityItemBinding
import com.kirkbushman.zammad.models.TicketPriority

class PrioritySpinnerAdapter(

    context: Context,
    textViewResourceId: Int,

    private val objects: List<TicketPriority>

) : ArrayAdapter<TicketPriority>(context, textViewResourceId, objects) {

    private val inflater = LayoutInflater.from(context)

    @ColorInt
    private val priorityImageArray = arrayOf(
        R.drawable.green_circle,
        R.drawable.lime_circle,
        R.drawable.yellow_circle,
        R.drawable.orange_circle,
        R.drawable.red_circle
    )

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layout = convertView ?: inflater.inflate(R.layout.spinner_priority_item, parent, false)
        val binding = SpinnerPriorityItemBinding.bind(layout)

        binding.prioritySpinnerText.text = getTextFromindex(objects[position].id.toString())
        binding.spinnerLogo.setImageResource(priorityImageArray[position])

        return layout
    }

    private fun getTextFromindex(s: String): String {
        when (s) {
            "1" -> return "Bassa Priorità"
            "2" -> return "Medio-Bassa Priorità"
            "3" -> return "Media Priorità"
            "4" -> return "Medio-Alta Priorità"
            "5" -> return "Alta Priorità"
        }

        return ""
    }
}
