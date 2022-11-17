package com.sg.alma55.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.gson.Gson
import com.sg.alma50a.post_drawing.DrawPostCenter
import com.sg.alma55.R
import com.sg.alma55.activities.PostDetailesActivity
import com.sg.alma55.activities_tt.VideoActivity
import com.sg.alma55.modeles.Post
import com.sg.alma55.post_drawing.DrawGeneralPost
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import com.sg.alma55.utilities.Constants.FALSE
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.Constants.SHARPREF_CURRENT_POST
import com.sg.alma55.utilities.Constants.SHARPREF_MOVING_BACKGROUND
import com.sg.alma55.utilities.Constants.TRUE
import com.sg.alma55.utilities.UtilityPost

import kotlin.collections.ArrayList


class PostAdapter(val context: Context, val posts: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    val util = UtilityPost()

    val base = BaseActivity()
    val pref = context.getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
    var movingBackgroundMode = pref.getString(SHARPREF_MOVING_BACKGROUND, TRUE)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindImage(posts[position])
        pref.edit().putInt(Constants.SHARPREF_CURRENT_POST_POSITION, position).apply()

    }
    /*
        holder.itemView.tour_image.clipToOutline = true
        Picasso.get().load(places[position].url).into(holder.itemView.tour_image)*/

    override fun getItemCount() = posts.size


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout = itemView?.findViewById<ConstraintLayout>(R.id.itemLayout)
        val image = itemView?.findViewById<ImageView>(R.id.pagerImage)
        val ken = itemView.findViewById<com.flaviofaria.kenburnsview.KenBurnsView>(R.id.tour_image)
        val postVideoBtn=itemView?.findViewById<Button>(R.id.videoBtn)

/*  fun bindImage(post: Post) {
            DrawGeneralPost().drawPost(context, post, layout)  // onClick include in here

             ken.load(post.imageUri){
               crossfade(true)
               crossfade(1000)
               transformations(RoundedCornersTransformation(30f))
           }


          //  Picasso.get().load(post.imageUri).into(ken)
            ken.resume()

           /* val adi=AccelerateDecelerateInterpolator()
            val generator= RandomTransitionGenerator(5000,adi)
             ken.setTransitionGenerator(generator)*/



            // pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, post.postNum).apply()

            // ken.clipToOutline = true

            image.setOnClickListener {
                val editor = pref.edit()
                val gson = Gson()
                val json: String = gson.toJson(post)
                editor.putString(SHARPREF_CURRENT_POST, json)
                editor.apply()
                context.startActivity(Intent(context, PostDetailesActivity::class.java))
            }
       */


        fun bindImage(post: Post) {
            DrawGeneralPost().drawPost(context, post, layout)
            image.setOnClickListener {
                val editor = pref.edit()
                val gson = Gson()
                val json: String = gson.toJson(post)
                editor.putString(SHARPREF_CURRENT_POST, json)
                editor.apply()
                context.startActivity(Intent(context, PostDetailesActivity::class.java))

            }
            if (post.videoText == Constants.NO_VALUE) {
                postVideoBtn.visibility=View.GONE
            } else {
                postVideoBtn.setOnClickListener {
                    val editor = pref.edit()
                    val gson = Gson()
                    val json: String = gson.toJson(post)
                    editor.putString(SHARPREF_CURRENT_POST, json)
                    editor.apply()
//                 val intent = Intent(context, VideoActivity::class.java)
//                 intent.putExtra(CURRENT_URL, post.videoUrl)
//                 context.startActivity(intent)
                    context.startActivity(Intent(context, VideoActivity::class.java))
                }
            }
        }
    }

}


/*class PostAdapter(val viewPager: ViewPager2, val context: Context, val posts: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.PagerViewHolder>() {

    val util = UtilityPost()
   val  pref=context.getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
       // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PagerViewHolder(view)
    }
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bindImage(posts[position])
        prepareMoreImage(position+1)

        //---------------------
        if (position == posts.size - 2) {
            viewPager.post(run)
        }

        /*viewPager.post{
            viewPager.setCurrentItem(10,true)
        }*/
        //------------------
    }

    private fun prepareMoreImage(position: Int) {
        var pos=position

        if (pos<posts.size){
            loadImage(pos)
        }else{
            pos=0
        }
        pos++
        if (pos<posts.size){
            loadImage(pos)
        }else{
            pos=0
        }

    }

    private fun loadImage(pos: Int) {

    }


    override fun getItemCount() = posts.size




    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout = itemView?.findViewById<ConstraintLayout>(R.id.itemLayout)
      //  val postImage = itemView?.findViewById<ImageView>(R.id.pagerImage)

        fun bindImage(post: Post) {
            pref.edit().putInt(Constants.SHARPREF_CURRENT_POST_NUM, post.postNum).apply()
             DrawGeneralPost().drawPost(context,post,layout)  // onClick include in here
        }

    }

    //-------------------
    val run = object : Runnable {            // for automate scrolling
        override fun run() {
            posts.addAll(posts)
            notifyDataSetChanged()
        }
    }
    //-------------
}*/