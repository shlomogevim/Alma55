package com.sg.alma55.modeles

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.sg.alma55.utilities.Constants.NO_VALUE
import kotlinx.parcelize.Parcelize


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
    var postPadding:ArrayList<Int> = arrayListOf<Int>(0,0),
    var textLocation:ArrayList<Int> = arrayListOf<Int>(100,0,0,0,0),
    var postTextColor:ArrayList<String> = arrayListOf<String>(),
    var postFontFamily:Int=0,
    var postRadiuas:Int=16,
    var timestamp: Timestamp?=null,
    var lineSpacing: Double? =1.4,
    var grade:Int=0,
    var videoUrl:String=NO_VALUE,
    var videoText:String=NO_VALUE
)


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



