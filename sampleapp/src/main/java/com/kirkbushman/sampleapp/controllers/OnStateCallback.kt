package com.kirkbushman.sampleapp.controllers

interface OnStateCallback : OnClickCallback {

    fun onUpdateClick(position: Int)
    fun onDeleteClick(position: Int)
}
