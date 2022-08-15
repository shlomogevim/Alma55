package com.sg.alma55.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.animation.AlphaAnimation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.databinding.ActivitySplashBinding
import com.sg.alma55.modeles.Post
import com.sg.alma55.modeles.User
import com.sg.alma55.models.Comment
import com.sg.alma55.utilities.*
import com.sg.alma55.utilities.Constants.COMMENT_REF
import com.sg.alma55.utilities.Constants.FALSE
import com.sg.alma55.utilities.Constants.POST_REF
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.Constants.SHARPREF_CURRENT_POST_NUM
import com.sg.alma55.utilities.Constants.SHARPREF_GRADE_ARRAY
import com.sg.alma55.utilities.Constants.SHARPREF_GRADE_ZERO
import com.sg.alma55.utilities.Constants.SHARPREF_MOVING_BACKGROUND
import com.sg.alma55.utilities.Constants.SHARPREF_POSTS_ARRAY
import com.sg.alma55.utilities.Constants.SHARPREF_SORT_BY_TIME_PUBLISH
import com.sg.alma55.utilities.Constants.SHARPREF_SORT_SYSTEM
import com.sg.alma55.utilities.Constants.SHARPREF_SPLASH_SCREEN_DELAY
import com.sg.alma55.utilities.Constants.SHARPREF_TOTAL_POSTS_SIZE
import com.sg.alma55.utilities.Constants.TRUE
import java.lang.reflect.Type

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    var currentUser: User? = null
    lateinit var pref: SharedPreferences
    var pressHelpBtn = false
    var delayInMicroSecond = 0
    lateinit var timer: CountDownTimer
    var posts = ArrayList<Post>()
    val comments = ArrayList<Comment>()
    lateinit var gradeArray: ArrayList<Int>
    lateinit var gradeHashMap: HashMap<Int, Int>
    val util = UtilityPost()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirestoreClass().getUserDetails(this)
        initData()
        activateButtoms()
   getHeadLines()
      retriveComments()
    downloadAllPost()

//       logi("aaaaaa")
//        val posts10= downloadAllPost()
//        Handler().postDelayed({
//            logi("bbbbbbbbbbbbbb")
//            showPosts(30)
//        }, 1000)

        //posts=downloadAllPost()

        /*   val posts10= downloadAllPost()
             showPosts10(posts10)*/
//       posts= loadPosts()
//        showPosts(2)

        // chkProblemInPosts()


        pauseIt()
    }


    fun downloadAllPost(): ArrayList<Post> {
        //var index=0

         posts.clear()
        //    var post1=Post()
        //    showPosts(0)
        FirebaseFirestore.getInstance().collection(POST_REF)
            // .orderBy(Constants.POST_TIME_STAMP, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    for (doc in value.documents) {
                        val post = util.retrivePostFromFirestore(doc)

                     if (post.postNum in 554..1000) {
                         posts.add(post)
                     }
                   //   index++
                    }

                    pref.edit().putInt(SHARPREF_TOTAL_POSTS_SIZE, posts.size).apply()
                    retriveGradeMapFromSharPref()
                   savePosts()
                  //  showPosts(0)
                }
            }

         return posts
    }

   /* private fun showPosts(ind: Int) {
      //  logi("SplashActivity 123  posts=${posts.joinToString()}  ind=$ind")
        for (post in posts) {
            showPost(ind, post)
        }
    }*/

   /* private fun showPost(ind: Int, post: Post) {
        //  if (post.postNum==1000){
//             if (post.postNum==901){
//             if (post.postNum==4940){
        //   logi("SplashActivity-> 102    ind=$ind   postNum=${post.postNum} post.postMargin=${post.postMargin.joinToString()} \n posts==>${posts.joinToString()}")
     //   logi("SplashActivity-> 102    ind=$ind   postNum=${post.postNum} post.postMargin=${post.postMargin.joinToString()} ")
        logi("SplashActivity-> 102    post==>$post ")
        logi("------------------------------")
        //     }
    }*/



    private fun showPost1(post: Post) {
        // if (post.postNum==1000){
//             if (post.postNum==901){
//             if (post.postNum==4940){
        logi("SplashActivity-->showPost1 123 (after reloading)    num=${post.postNum}  post.postMargin=${post.postMargin.joinToString()}")
        // }
    }
    /* private fun chkCondition():Boolean{

     }*/

    fun savePosts() {
        pref.edit().putString(SHARPREF_GRADE_ZERO, "true").apply()
        for (post in posts) {
            if (post.grade > 0) {
                pref.edit().putString(SHARPREF_GRADE_ZERO, "false").apply()
                break
            }
        }
   //     logi("SplashActivity 146   posts=${posts.joinToString()}")
        pref.edit().remove(SHARPREF_POSTS_ARRAY).commit()
        val editor = pref.edit()
        val gson = Gson()
        val json: String = gson.toJson(posts)
        editor.putString(SHARPREF_POSTS_ARRAY, json)
        editor.apply()
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


    private fun initData() {
        gradeArray = arrayListOf()
        pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
        pref.edit().remove(SHARPREF_ALMA).apply()
        pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, 0).apply()
        pref.edit().putString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_TIME_PUBLISH).apply()
        pref.edit().putString(SHARPREF_MOVING_BACKGROUND, TRUE).apply()
        delayInMicroSecond = pref.getInt(SHARPREF_SPLASH_SCREEN_DELAY, 10) * 1000
    }

    fun getingUserData(user: User) {
        currentUser = user
        getHeadLines()
    }

    private fun activateButtoms() {
        binding.btnHelp.setOnClickListener {
            pressHelpBtn = true
            startActivity(Intent(this, HelpActivity::class.java))
        }
    }

    private fun getHeadLines() {
        setFirstHello()
        timerWorks()
    }

    private fun setFirstHello() {
        val name: String = getUserName()
        val helloSt = "מה קורה " + "\n" +
                "" + "\n" +
                name + "\n" +
                "" + "\n" +
                "מה הענינים " + "\n" +
                "" + "\n" +
                "איך ככה " + "\n" +
                ""
        binding.tvText1.text = helloSt
    }

    private fun getUserName(): String {
        if (currentUser != null) {
            return "${currentUser!!.userName} ${currentUser!!.lastName} "
        } else {
            return "אורח"
        }
    }

    private fun timerWorks() {
//          lottie.animate().translationY(1400f).setDuration(1000).setStartDelay(4000)
        // starteAnimateLottie()
        val animate1 = AlphaAnimation(0f, 1.0f)
        animate1.duration = 5000
        binding.lottie.startAnimation(animate1)
        val st1 = "אל תתיאש עוד: " + "\n" + "\n"
        val st2 = "   שניות   " + "\n" + "\n" +
                "  תתחיל האפליקציה לעבוד "
        binding.lottie.playAnimation()
        timer = object : CountDownTimer(delayInMicroSecond.toLong(), 1000) {
            override fun onTick(remaning: Long) {
                //if (remaning==(delayInMicroSecond.toLong())){
                //    binding.lottie.playAnimation()
                //  }
                //  binding.lottie.pauseAnimation()
                val totalMessage = st1 + (remaning / 1000).toString() + st2
                binding.tvText2.text = totalMessage
            }

            override fun onFinish() {}
        }
    }

    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    private fun retriveComments() {
        comments.clear()
        FirebaseFirestore.getInstance().collection(COMMENT_REF)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    for (doc in value.documents) {
                        val comment = util.retriveCommentFromFirestore(doc)
                        comments.add(comment)
                    }
                    saveComments()

                }
            }
    }


    private fun retriveGradeMapFromSharPref() {
        val gson = Gson()
        var storeMappingString = pref.getString(SHARPREF_GRADE_ARRAY, "oppsNotExist")

        // storeMappingString="oppsNotExist"    //**********

        //  logi("Splash 159      storeMappingString=$storeMappingString")
        if (storeMappingString == "oppsNotExist") {
            val gradeMap: HashMap<Int, Int> = hashMapOf()
            for (index in 0 until posts.size) {
                val post = posts[index]
                gradeMap[post.postNum] = 0
                post.grade = 0
            }
            val hashMapString = gson.toJson(gradeMap)
            pref.edit().putString(SHARPREF_GRADE_ARRAY, hashMapString).apply()
            pref.edit().putString(SHARPREF_GRADE_ZERO, TRUE).apply()
        } else {
            val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
            gradeHashMap = gson.fromJson(storeMappingString, type)
            for (entery in gradeHashMap) {
                val post: Post = findPost(entery.key)
                post.grade = entery.value
            }
            val bo = chkPostsGradeIsZero()
            if (bo) {
                pref.edit().putString(SHARPREF_GRADE_ZERO, TRUE).apply()
            } else {
                pref.edit().putString(SHARPREF_GRADE_ZERO, FALSE).apply()
            }
        }
    }

    private fun chkPostsGradeIsZero(): Boolean {
        for (item in posts) {
            if (item.grade > 0) {
                return false
            }
        }
        return true
    }

    private fun findPost(key: Int): Post {
        //val post = Post()
        for (post in posts) {
            if (post.postNum == key) {
                return post
            }
        }
        return Post()
    }


    fun saveComments() {
        val editor = pref.edit()
        val gson = Gson()
        val json: String = gson.toJson(comments)
        editor.putString(Constants.SHARPREF_COMMENTS_ARRAY, json)
        editor.apply()
    }

    private fun pauseIt() {
        /*   val sortSystem = pref.getString(SHARPREF_SORT_SYSTEM, SHARPREF_SORT_BY_TIME_PUBLISH).toString()
           logi("SplashActivity 251      sortSystem=$sortSystem")*/
        Handler().postDelayed(
            {
                if (!pressHelpBtn) {
                    //chkProblemInPosts()
                   startActivity(Intent(this, MainActivity::class.java))
//                   startActivity(Intent(this, HelpActivity::class.java))
                }
//           }, delayInMicroSecond.toLong()
            }, 0
        )
    }


    /* private fun setText() {
         var name = ""
         name = if (currentUser != null) {
             "${currentUser!!.userName} ${currentUser!!.lastName} "
         } else {
             "אורח"
         }

         binding.tvText1.text = "ברוך הבא "
         binding.tvText2.text = name
     }*/

    /* private fun starteAnimateLottie() {
         val animate1= AlphaAnimation(0.2f, 1.0f)
         animate1.duration=2000

         *//*AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
animation1.setDuration(1000);
animation1.setStartOffset(5000);
animation1.setFillAfter(true);
iv.startAnimation(animation1);*//*
    }*/

//    private fun saveUserName() {
//        var currentUserID = FirestoreClass().getCurrentUserID()
////        logi("SplashActivity 98    currentUserID=$currentUserID")
////currentUserID=""
//
//        if (currentUserID !="") {
//            //  FirestoreClass().getUserDetails(this)
//            FirebaseFirestore.getInstance().collection(USER_REF).document(currentUserID)
//                .get()
//                .addOnSuccessListener { document ->
//                    val user = document.toObject(User::class.java)!!
//                    currentUser=user
//                    currentUseName=user.userName
//                    pref.edit().putString(SHARPREF_CURRENT_USER_NAME,"${user.userName}").apply()
//                }
//        }else{
//            pref.edit().putString(SHARPREF_CURRENT_USER_NAME,"אורח").apply()
//        }
//    }
}

