package com.kirkbushman.sampleapp.controllers

interface OnUpDelCallback : OnClickCallback {

    fun onUpdateClick(position: Int)
    fun onDeleteClick(position: Int)
}
