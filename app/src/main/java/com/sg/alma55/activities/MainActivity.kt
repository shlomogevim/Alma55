package com.sg.alma55.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.R
import com.sg.alma55.activities_tt.VideoActivity
import com.sg.alma55.adapters.PostAdapter
import com.sg.alma55.databinding.ActivityMainBinding
import com.sg.alma55.modeles.Post
import com.sg.alma55.modeles.User
import com.sg.alma55.models.Comment
import com.sg.alma55.utilities.*
import com.sg.alma55.utilities.Constants.FALSE
import com.sg.alma55.utilities.Constants.NO_VALUE
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.Constants.SHARPREF_COMMENTS_ARRAY
import com.sg.alma55.utilities.Constants.SHARPREF_CURRENT_POST_NUM
import com.sg.alma55.utilities.Constants.SHARPREF_GRADE_ZERO
import com.sg.alma55.utilities.Constants.SHARPREF_MOVING_BACKGROUND
import com.sg.alma55.utilities.Constants.SHARPREF_POSTS_ARRAY
import com.sg.alma55.utilities.Constants.SHARPREF_SORT_BY_GRADE
import com.sg.alma55.utilities.Constants.SHARPREF_SORT_BY_RECOMMENDED
import com.sg.alma55.utilities.Constants.SHARPREF_SORT_BY_TIME_PUBLISH
import com.sg.alma55.utilities.Constants.SHARPREF_SORT_SYSTEM
import java.lang.reflect.Type

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    val util = UtilityPost()
    var posts = ArrayList<Post>()
    val comments = ArrayList<Comment>()
    private var currentUser: User? = null
    lateinit var rvPosts: RecyclerView
    lateinit var postAdapter: PostAdapter
    lateinit var pref: SharedPreferences
    lateinit var gson: Gson
    var sortSystem = "NoValue"
  //  lateinit var currentPost: Post
    var currentPostNum = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gson = Gson()
        rvPosts = binding.rvPosts
        pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)

        pref.edit().putString(SHARPREF_MOVING_BACKGROUND, FALSE).apply()

        currentPostNum = pref.getInt(SHARPREF_CURRENT_POST_NUM, 0)

        sortSystem = pref.getString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_TIME_PUBLISH).toString()
       // downloadAllPost()
//logi("MainActivity 53     onCreate      sortSystem=$sortSystem")
        FirestoreClass().getUserDetails(this)
        setSortSystemBackground()

       // setVideoBtn()



    }


   /* private fun setVideoBtn() {
        val btn=binding.videoBtn
        btn.setOnClickListener {
            currentPost = loadCurrentPost()
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }
    }*/

    fun loadCurrentPost(): Post {
        val gson = Gson()
        val json: String? = pref.getString(Constants.SHARPREF_CURRENT_POST, null)
        val type: Type = object : TypeToken<Post>() {}.type
        val post: Post = gson.fromJson(json, type)
        return post
    }


    override fun onResume() {
        super.onResume()
//       logi("MainActivity 63   onResume        sortSystem$sortSystem")
        posts.clear()
      //  downloadAllPost()
      posts = loadPosts()
//          setNoValue()

          sortSystem = pref.getString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_TIME_PUBLISH).toString()
          currentPostNum = pref.getInt(SHARPREF_CURRENT_POST_NUM, 0)

  //        val itZero = pref.getString(SHARPREF_GRADE_ZERO, "toto").toString()
  //        logi("SplashActivity  70     itZero=$itZero")

          FirestoreClass().getUserDetails(this)
          setSortSystemBackground()
          sortPosts()
          if (currentPostNum == 0) {
              currentPostNum = posts[0].postNum
          }
          create_rvPost()
          moveIt()
    }

    private fun setNoValue() {
         /*for (item in posts){

//             if (item.postNum==1001) {

                // item.videoUrl= NO_VALUE
                if (item.videoUrl.equals(null)) item.videoUrl= "popi"

                 logi(                     "gg",
                     "MainActivity 117 ==>  postNum=${item.postNum} videoUrl=${item.videoUrl}"
                 )
//             }

         }*/
    }

    fun getCurrentUser(user: User) {
        currentUser=user
    }
    private fun setSortSystemBackground() {
        if (sortSystem==SHARPREF_SORT_BY_RECOMMENDED){
            binding.mainLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundRecommended))
        }
        if (sortSystem== SHARPREF_SORT_BY_TIME_PUBLISH){
            binding.mainLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundTimePublish))
        }
        if (sortSystem== SHARPREF_SORT_BY_GRADE){
            binding.mainLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundGrade))
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun create_rvPost() {
        val layoutManger = CenterZoomLayout(this)
        layoutManger.orientation = LinearLayoutManager.HORIZONTAL
        layoutManger.reverseLayout = true
        // layoutManger.stackFromEnd=true
        rvPosts.layoutManager = layoutManger

        val snapHelper = LinearSnapHelper()
        rvPosts.setOnFlingListener(null)
        snapHelper.attachToRecyclerView(rvPosts)
        rvPosts.isNestedScrollingEnabled = false

        postAdapter = PostAdapter(this, posts)
        rvPosts.adapter = postAdapter
        rvPosts.setHasFixedSize(true)
        postAdapter.notifyDataSetChanged()
    }

    private fun sortPosts() {
        if (sortSystem == SHARPREF_SORT_BY_RECOMMENDED) {
            posts.sortWith(compareByDescending({ it.postId }))                 //postId show recommended factor

        }
        if (sortSystem == SHARPREF_SORT_BY_TIME_PUBLISH) {
            posts.sortWith(compareByDescending({ it.timestamp }))
            //  logi("MainActivity in sortPosts  111       sortSystem=$sortSystem       posts.size=${posts.size}")
        }
//          persons.sortWith(compareBy({ it.name }, { it.age }))
        if (sortSystem == SHARPREF_SORT_BY_GRADE) {
            posts.removeAll({ it.grade == 0 })
             logi("MainActivity in sortPosts  125       sortSystem=$sortSystem       posts.size=${posts.size}")
            if (posts.size==0){
                sortSystem == SHARPREF_SORT_BY_RECOMMENDED
                posts.sortWith(compareByDescending({ it.postId }))
            }else{
                posts.sortWith(compareByDescending({ it.grade }))
            }
            // logi("MainActivity in sortPosts  121    sortSystem=$sortSystem       posts.size=${posts.size}")
        }
    }

    private fun loadPosts(): ArrayList<Post> {
        posts.clear()
        val gson = Gson()
        val json: String? = pref.getString(SHARPREF_POSTS_ARRAY, null)
        val type: Type = object : TypeToken<ArrayList<Post>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        val arr: ArrayList<Post> = gson.fromJson(json, type)
        return arr
    }


    private fun moveIt() {
        //logi("MainActivity 129   currentPostNum=$currentPostNum")

        Handler().postDelayed(
            {
                for (counter in 0 until posts.size) {
                    if (posts[counter].postNum == currentPostNum) {
                        rvPosts.scrollToPosition(counter)
                        // logi("MainActivity 136   counter=$counter")
                    }
                }
            }, 100
        )
    }

}
