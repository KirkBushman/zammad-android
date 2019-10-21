package com.kirkbushman.sampleapp.controllers

interface OnOrganizationCallback : OnClickCallback {

    fun onUpdateClick(position: Int)
    fun onDeleteClick(position: Int)
}
