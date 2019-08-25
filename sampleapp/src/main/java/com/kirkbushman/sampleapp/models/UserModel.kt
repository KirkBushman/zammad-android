package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.User

@EpoxyModelClass(layout = R.layout.item_user)
abstract class UserModel : EpoxyModelWithHolder<UserHolder>() {

    @EpoxyAttribute
    lateinit var user: User

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: UserHolder) {

        val userNameText = "${user.firstname} ${user.lastname}"
        holder.userName.text = userNameText
        holder.userId.text = user.id.toString()
        holder.userCreated.text = user.createdAt.toString()

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: UserHolder) {
        holder.container.setOnClickListener(null)
    }
}

class UserHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val userName by bind<TextView>(R.id.user_name)
    val userId by bind<TextView>(R.id.user_id)
    val userCreated by bind<TextView>(R.id.user_created)
}
