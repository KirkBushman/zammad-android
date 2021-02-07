package com.kirkbushman.sampleapp.callbacks

interface OnTicketCallback : OnClickCallback {

    fun onArticleClick(position: Int)
    fun onUpdateClick(position: Int)
    fun onDeleteClick(position: Int)
}
