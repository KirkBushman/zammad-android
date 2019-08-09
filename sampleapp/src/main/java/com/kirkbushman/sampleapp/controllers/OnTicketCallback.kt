package com.kirkbushman.sampleapp.controllers

interface OnTicketCallback : OnClickCallback {

    fun onArticleClick(position: Int)
    fun onUpdateClick(position: Int)
    fun onDeleteClick(position: Int)
}
