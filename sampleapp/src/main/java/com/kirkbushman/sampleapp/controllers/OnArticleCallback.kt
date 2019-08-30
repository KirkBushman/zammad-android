package com.kirkbushman.sampleapp.controllers

interface OnArticleCallback : OnClickCallback {

    fun onAttachmentsClick(position: Int)
}
