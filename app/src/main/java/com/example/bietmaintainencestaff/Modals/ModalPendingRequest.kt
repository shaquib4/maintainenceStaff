package com.example.bietmaintainencestaff.Modals

class ModalPendingRequest(
    val requestId:String,
    val roomNo:String,
    val requestedBy:String,
    val problem:String,
    val hostelName:String,
    val mobNo:String,
    val status:String,
    val reqById:String

) {
    constructor():this("","","","","","","","")
}