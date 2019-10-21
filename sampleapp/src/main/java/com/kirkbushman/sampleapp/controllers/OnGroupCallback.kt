package com.kirkbushman.sampleapp.controllers

interface OnGroupCallback : OnClickCallback {

    fun onUpdateClick(position: Int)
    fun onDeleteClick(position: Int)
}
