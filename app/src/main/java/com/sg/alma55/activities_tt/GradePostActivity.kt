package com.sg.alma55.activities_tt

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityGradePostBinding
import com.sg.alma55.modeles.Post
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import com.sg.alma55.utilities.Constants.FALSE
import com.sg.alma55.utilities.Constants.SHARPREF_CURRENT_POST_NUM
import com.sg.alma55.utilities.Constants.SHARPREF_GRADE_ARRAY
import com.sg.alma55.utilities.Constants.SHARPREF_GRADE_ZERO
import com.sg.alma55.utilities.Constants.SHARPREF_TOTAL_POSTS_SIZE
import com.sg.alma55.utilities.Constants.TRUE
import java.lang.reflect.Type

class GradePostActivity : BaseActivity() {
    private lateinit var binding: ActivityGradePostBinding
    lateinit var pref: SharedPreferences
    var posts = ArrayList<Post>()
    lateinit var gradeHashMap: HashMap<Int,Int>
    lateinit var  gson : Gson
    private var totalPostsNun=0
    private var currentPostNum=0
    private var gradeSt=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGradePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref=getSharedPreferences(Constants.SHARPREF_ALMA , Context.MODE_PRIVATE)
        totalPostsNun=pref.getInt(SHARPREF_TOTAL_POSTS_SIZE,0)
        currentPostNum = pref.getInt(SHARPREF_CURRENT_POST_NUM, 0)
        gradeHashMap=HashMap()
        gson = Gson()
        posts.clear()
        posts = loadPosts()

        val storeMappingString=pref.getString(SHARPREF_GRADE_ARRAY,"oppsNotExist")
//        logi("GradePostActivity 33  storeMappingString=$storeMappingString")
        if (storeMappingString!="oppsNotExist") {
            val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
            gradeHashMap=gson.fromJson(storeMappingString,type)
            val currentGradNum=gradeHashMap[currentPostNum]
            if (currentGradNum==null || currentGradNum==0){
                gradeSt="הפוסט הזה לא מדורג אצלך עדיין ..."
            }else{
                val st1="הדירוג הנוכחי של הפוסט :"
                gradeSt="$st1 $currentGradNum"
            }
            //   logi("GradePostActivity 38  currentGradNum=$currentGradNum")
            binding.etGradeNum.hint=gradeSt
        }

        binding.btnPassPost.setOnClickListener {
            val newCurrentPostGrade=binding.etGradeNum.text.toString().toInt()
            val postNumInPosts:Int=getPostNum(currentPostNum)

            if (newCurrentPostGrade>=0 && newCurrentPostGrade<=100) {
                logi("GradePostActivity 65        currentPostNum=$currentPostNum")
                 posts[postNumInPosts].grade=newCurrentPostGrade
                gradeHashMap[currentPostNum] = newCurrentPostGrade
                val hashMapString = gson.toJson(gradeHashMap)
                pref.edit().putString(SHARPREF_GRADE_ARRAY, hashMapString).apply()
                pref.edit().putString(SHARPREF_GRADE_ZERO, FALSE).apply()
                savePosts()
               logi("GradePostActivity 63 GradeIsZero    -----> false ")
                finish()
            }else{
                showErrorSnackBar("צריך להכניס מספר בין 0 ל 100", true)
            }
        }
    }

    private fun getPostNum(currentPostNum: Int): Int {
         val num=0
         for (index  in 0 until posts.size){
             if (posts[index].postNum==currentPostNum){
                 return index
                 break
             }
         }
        return num
    }

    fun loadPosts(): ArrayList<Post> {
        posts.clear()
        val gson = Gson()
        val json: String? = pref.getString(Constants.SHARPREF_POSTS_ARRAY, null)
        val type: Type = object : TypeToken<ArrayList<Post>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        val arr: ArrayList<Post> = gson.fromJson(json, type)
        return arr
    }
    fun savePosts() {
       /* pref.edit().putString(SHARPREF_GRADE_ZERO, TRUE).apply()
        for (post in posts) {
            if (post.grade > 0) {
                pref.edit().putString(SHARPREF_GRADE_ZERO, FALSE).apply()
                break
            }
        }*/
        val editor = pref.edit()
        val gson = Gson()
        val json: String = gson.toJson(posts)
        editor.putString(Constants.SHARPREF_POSTS_ARRAY, json)
        editor.apply()
    }
}

