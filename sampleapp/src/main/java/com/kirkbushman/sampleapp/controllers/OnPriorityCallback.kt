package com.kirkbushman.sampleapp.controllers

interface OnPriorityCallback : OnClickCallback {

    fun onUpdateClick(position: Int)
    fun onDeleteClick(position: Int)
}
