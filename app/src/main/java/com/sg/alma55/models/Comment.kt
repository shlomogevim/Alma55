package com.sg.alma55.models

import com.google.firebase.Timestamp


data class Comment(
    var commntId:String="",
    var postNumString:String="",
    var text:String="",
    var userName:String="",
    var userId:String="",
    var timestamp: Timestamp?=null,
    var index:String="0"
)
