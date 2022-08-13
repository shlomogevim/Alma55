package com.sg.alma55.modeles

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.sg.alma55.utilities.Constants.CONSTANT
import kotlinx.parcelize.Parcelize


data class Post(
    var postId:Int=0,
    var postNum:Int=1,
    var lineNum: Int =1,
    var imageUri:String="",
    var postText: ArrayList<String> = arrayListOf<String>("no data"),
    var postMargin: ArrayList<ArrayList<Int>> =arrayListOf<ArrayList<Int>>(),
    var postBackground:String="",
    var postTransparency:Int=0,
    var postTextSize:ArrayList<Int> = arrayListOf<Int>(0,14),
    var postPadding:ArrayList<Int> = arrayListOf<Int>(0,0),
    var textLocation:ArrayList<Int> = arrayListOf<Int>(102, -1, 30,10, 0, -60,0, 0),
    var postTextColor:ArrayList<String> = arrayListOf<String>(CONSTANT,"#000000"),
    var postFontFamily:Int=0,
    var postRadiuas:Int=0,
    var timestamp: Timestamp?=null,
    var lineSpacing:Float=1.4f,
    var grade:Int=0
)
// val col = "#$textcolo"
//            postTextColor = arrayListOf(CONSTANT, col)

/*

data class Post(
    var postId:Int=0,
    var postNum:Int=1,
    var lineNum: Int =1,
    var imageUri:String="",
    var postText: ArrayList<String> = arrayListOf<String>(),
    var postMargin: ArrayList<ArrayList<Int>> =arrayListOf<ArrayList<Int>>(),
    var postBackground:String="",
    var postTransparency:Int=0,
    var postTextSize:ArrayList<Int> = arrayListOf<Int>(),
    var postPadding:ArrayList<Int> = arrayListOf<Int>(),
    var postTextColor:ArrayList<String> = arrayListOf<String>(),
    var postFontFamily:Int=0,
    var postRadiuas:Int=0,
    var timestamp: Timestamp?=null,
    var lineSpacing: Double? =1.4,
    var grade:Int=0
)
*/



