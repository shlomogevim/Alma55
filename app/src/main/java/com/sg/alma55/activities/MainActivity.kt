package com.sg.alma55.activities

import android.content.Context
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
import com.sg.alma55.adapters.PostAdapter
import com.sg.alma55.databinding.ActivityMainBinding
import com.sg.alma55.modeles.Post
import com.sg.alma55.modeles.User
import com.sg.alma55.models.Comment
import com.sg.alma55.utilities.*
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.Constants.SHARPREF_COMMENTS_ARRAY
import com.sg.alma55.utilities.Constants.SHARPREF_CURRENT_POST_NUM
import com.sg.alma55.utilities.Constants.SHARPREF_GRADE_ZERO
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
    var currentPostNum = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gson = Gson()
        rvPosts = binding.rvPosts
        pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
        currentPostNum = pref.getInt(SHARPREF_CURRENT_POST_NUM, 0)
        sortSystem = pref.getString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_TIME_PUBLISH).toString()
       // downloadAllPost()
//logi("MainActivity 53     onCreate      sortSystem=$sortSystem")
        FirestoreClass().getUserDetails(this)
        setSortSystemBackground()

    }

    override fun onResume() {
        super.onResume()
//       logi("MainActivity 63   onResume        sortSystem$sortSystem")
        posts.clear()
      //  downloadAllPost()
      posts = loadPosts()

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
   /* fun downloadAllPost(): ArrayList<Post> {
        posts.clear()
        FirebaseFirestore.getInstance().collection(Constants.POST_REF)
            // .orderBy(Constants.POST_TIME_STAMP, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    for (doc in value.documents) {
                        val post = util.retrivePostFromFirestore(doc)
                        posts.add(post)
                        showPost(post)
                    }

                }
            }
        return posts
    }*/
  /*  private fun showPost(post:Post) {
        if (post.postNum==1000){
//             if (post.postNum==901){
//             if (post.postNum==4940){
            logi("MainActivity  171    post.postMargin=${post.postMargin.joinToString()}")
        }
    }*/

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



    fun loadComments(): ArrayList<Comment> {
        comments.clear()
        val gson = Gson()
        val json: String? = pref.getString(SHARPREF_COMMENTS_ARRAY, null)
        val type: Type = object : TypeToken<ArrayList<Comment>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        val arr: ArrayList<Comment> = gson.fromJson(json, type)
        return arr
    }


}
