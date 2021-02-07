package com.kirkbushman.sampleapp.callbacks

interface OnUpDelCallback : OnClickCallback {

    fun onUpdateClick(position: Int)
    fun onDeleteClick(position: Int)
}
