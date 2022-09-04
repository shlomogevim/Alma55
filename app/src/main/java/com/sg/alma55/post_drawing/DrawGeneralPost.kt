package com.sg.alma55.post_drawing

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.RoundedCorner
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sg.alma55.R
import com.sg.alma55.modeles.Post
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants.CONSTANT
import com.sg.alma55.utilities.Constants.FALSE
import com.sg.alma55.utilities.Constants.NO_BOTTOM
import com.sg.alma55.utilities.Constants.NO_TOP
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.Constants.SHARPREF_MOVING_BACKGROUND
import com.sg.alma55.utilities.Constants.TRUE
import com.sg.alma55.utilities.FontFamilies
import com.sg.alma55.utilities.Utility
import java.util.*
import java.util.logging.Handler
import kotlin.concurrent.schedule

class DrawGeneralPost() : BaseActivity() {
    val util = Utility()
    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
    val helper = FontFamilies()
    lateinit var pref: SharedPreferences

    var constraintSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
    }

  /*  fun drawPostFire(context: Context, post: Post, layout: ConstraintLayout) {

        val pref = context.getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
        var movingBackgroundMode = pref.getString(SHARPREF_MOVING_BACKGROUND, FALSE)

        //util.logi("DrawGeneralPost 100      \n     =========>  /n post=$post")
        val textView1 = layout.findViewById<TextView>(R.id.tv1Fire)
        val textView2 = layout.findViewById<TextView>(R.id.tv2Fire)
        val textView3 = layout.findViewById<TextView>(R.id.tv3Fire)
        val textView4 = layout.findViewById<TextView>(R.id.tv4Fire)
        val textView5 = layout.findViewById<TextView>(R.id.tv5Fire)
        val textView6 = layout.findViewById<TextView>(R.id.tv6Fire)
        val textView7 = layout.findViewById<TextView>(R.id.tv7Fire)
        val textView8 = layout.findViewById<TextView>(R.id.tv8Fire)
        val textView9 = layout.findViewById<TextView>(R.id.tv9Fire)
        val textView10 = layout.findViewById<TextView>(R.id.tv10Fire)
        var textView = layout.findViewById<TextView>(R.id.tv10Fire)
        val image = layout.findViewById<ImageView>(R.id.pagerImageFire)
        val ken = layout.findViewById<com.flaviofaria.kenburnsview.KenBurnsView>(R.id.tour_image)

        textView1.text = ""
        textView2.text = ""
        textView3.text = ""
        textView4.text = ""
        textView5.text = ""
        textView6.text = ""
        textView7.text = ""
        textView8.text = ""
        textView9.text = ""
        textView10.text = ""

        //util.logi("DrawGeneralPost 110     \n     =========>  /n layout=$layout")

        //  image.load(post.imageUri)

        if (movingBackgroundMode == TRUE) {
            ken.load(post.imageUri) {
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(30f))
            }
            ken.resume()
        } else {
            image.load(post.imageUri) {
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(30f))
            }
            ken.pause()
        }

        constraintSet.clone(layout)

        for (index in 1..post.lineNum) {
            textView = when (index) {
                1 -> textView1
                2 -> textView2
                3 -> textView3
                4 -> textView4
                5 -> textView5
                6 -> textView6
                7 -> textView7
                8 -> textView8
                9 -> textView9
                10 -> textView10
                else -> textView1
            }

            createTextView(index, textView, post, context, layout)
            locateTextView(index, textView, post)

        }

        constraintSet.applyTo(layout)


    }*/


    fun drawPost(context: Context, post: Post, layout: ConstraintLayout) {

        val pref = context.getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
        var movingBackgroundMode = pref.getString(SHARPREF_MOVING_BACKGROUND, FALSE)

       /* if (post.postNum==1000){
            logi("DrawGeneral 56   newPost1=${post}")
        }*/

        // util.logi("DrawGeneralPost 100      \n     =========>  /n post=$post")
        val textView1 = layout.findViewById<TextView>(R.id.tv1)
        val textView2 = layout.findViewById<TextView>(R.id.tv2)
        val textView3 = layout.findViewById<TextView>(R.id.tv3)
        val textView4 = layout.findViewById<TextView>(R.id.tv4)
        val textView5 = layout.findViewById<TextView>(R.id.tv5)
        val textView6 = layout.findViewById<TextView>(R.id.tv6)
        val textView7 = layout.findViewById<TextView>(R.id.tv7)
        val textView8 = layout.findViewById<TextView>(R.id.tv8)
        val textView9 = layout.findViewById<TextView>(R.id.tv9)
        val textView10 = layout.findViewById<TextView>(R.id.tv10)
        var textView = layout.findViewById<TextView>(R.id.tv10)
        val image = layout.findViewById<ImageView>(R.id.pagerImage)
        val ken = layout.findViewById<com.flaviofaria.kenburnsview.KenBurnsView>(R.id.tour_image)

        textView1.text = ""
        textView2.text = ""
        textView3.text = ""
        textView4.text = ""
        textView5.text = ""
        textView6.text = ""
        textView7.text = ""
        textView8.text = ""
        textView9.text = ""
        textView10.text = ""

        if (movingBackgroundMode== TRUE){
            ken.load(post.imageUri){
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(30f))
            }
            ken.resume()
        }else{
            image.load(post.imageUri){
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(30f))
            }
            ken.pause()
        }
        constraintSet.clone(layout)

        for (index in 1..post.lineNum) {
            textView = when (index) {
                1 -> textView1
                2 -> textView2
                3 -> textView3
                4 -> textView4
                5 -> textView5
                6 -> textView6
                7 -> textView7
                8 -> textView8
                9 -> textView9
                10 -> textView10
                else -> textView1
            }

            createTextView(index, textView, post, context, layout)
            //  showPost(post)
            locateTextView(index, textView, post)
        }
        constraintSet.applyTo(layout)

    }
    private fun showPost(post:Post) {
        if (post.postNum==1000){
//             if (post.postNum==901){
//             if (post.postNum==4940){
            logi("DrawGeneral 135   post.postMargin=${post.postMargin.joinToString()}")
        }
    }

   /* private fun createTextView(
        index: Int, textView: TextView, post: Post, context: Context, layout: ConstraintLayout
    ) {

    *//*    logi("DrawGeneral 131     post.postNum= ${post.postNum}     \n" +
                "post.postMargin=${post.postMargin.joinToString()}")*//*

        for (index in 1..post.postTextColor.size - 1) {
            var col = post.postTextColor[index]
            //  util.logi("Draw GeneralPost 147    col=$col")
            col = col.replace("$", "")
            col = col.replace("#", "")
            col = "#$col"
            post.postTextColor[index] = col

        }
        val ind = index - 1
        //  logi("Draw General 117     postNum=${post.postNum}  lineSpacing= ${post.lineSpacing}")
        textView.setLineSpacing(1f, post.lineSpacing!!.toFloat())

        textView.text = post.postText[ind]
        if (post.postTextColor[0] == CONSTANT) {
            textView.setTextColor(Color.parseColor(post.postTextColor[1]))
        } else {
            textView.setTextColor(Color.parseColor(post.postTextColor[1]))
        }
        if (post.postTextSize[0] == 0) {
            textView.textSize = post.postTextSize[1].toFloat()
        } else {
            textView.textSize = post.postTextSize[index].toFloat()
        }
        val tra = helper.getTransfo(post.postTransparency)
        val shape = GradientDrawable()
        shape.cornerRadius = post.postRadiuas.toPx().toFloat()
        // util.logi("Draw GeneralPost 164  post.postBackground= ${post.postBackground}")

        post.postBackground = post.postBackground.replace("#", "")
        post.postBackground = post.postBackground.replace("$", "")

        shape.setColor(Color.parseColor("#$tra${post.postBackground}"))
        textView.background = shape
        val fontAddress = helper.getFamilyFont(post.postFontFamily)

        textView.typeface = ResourcesCompat.getFont(context, fontAddress)
        if (post.postNum == 358) {
            //   logi(" Draw GeneralPost 143    postPadding=${post.postPadding.joinToString()}")
        }
        textView.setPadding(
//          post.postPadding[0].toPx(),
//          post.postPadding[1].toPx(),
//          post.postPadding[2].toPx(),
//          post.postPadding[3].toPx()
            0.toPx(),
            post.postPadding[1].toPx(),
            0.toPx(),
            post.postPadding[3].toPx()
        )
        textView.gravity = Gravity.CENTER

    }*/
   private fun createTextView(
       index: Int, textView: TextView, post: Post, context: Context, layout: ConstraintLayout
   ) {
//       util.logi("Draw GeneralPost 142  ${post.postTextColor.joinToString()}")

       for (index in 1..post.postTextColor.size - 1) {
           /* if (!post.postTextColor[index].contains("#")) {
                post.postTextColor[index] = "#" + post.postTextColor[index]
            }*/
           var col = post.postTextColor[index]
//           util.logi("Draw GeneralPost 147    col=$col")
           col = col.replace("$", "")
           col = col.replace("#", "")
           col = "#$col"
           post.postTextColor[index] = col

           //  util.logi("Draw GeneralPost 152  ${post.postTextColor.joinToString()}")
       }
       val ind = index - 1
       //   val add=textView.lineHeight
     //  textView.setLineSpacing(1f, post.lineSpacing)
       post.lineSpacing?.let { textView.setLineSpacing(1f, it.toFloat()) }


/*int lineHeight = textView.getLineHeight();
float add = tvSampleText.getLineSpacingExtra();          // API 16+
float mult = tvSampleText.getLineSpacingMultiplier(); */
       /*textView.setLineSpacing(float add, float mult)*/


       textView.text = post.postText[ind]
//  util.logi("Draw GeneralPost 149   ${post.postTextColor.joinToString()}")
       if (post.postTextColor[0] == CONSTANT) {
           textView.setTextColor(Color.parseColor(post.postTextColor[1]))
       } else {
           textView.setTextColor(Color.parseColor(post.postTextColor[1]))
       }
       if (post.postTextSize[0] == 0) {
           textView.textSize = post.postTextSize[1].toFloat()
       } else {
           textView.textSize = post.postTextSize[index].toFloat()
       }
       val tra = helper.getTransfo(post.postTransparency)
       val shape = GradientDrawable()
       shape.cornerRadius = post.postRadiuas.toPx().toFloat()
// util.logi("Draw GeneralPost 164  post.postBackground= ${post.postBackground}")

       post.postBackground = post.postBackground.replace("#", "")
       post.postBackground = post.postBackground.replace("$", "")

       shape.setColor(Color.parseColor("#$tra${post.postBackground}"))
       textView.background = shape
       val fontAddress = helper.getFamilyFont(post.postFontFamily)
       textView.typeface = ResourcesCompat.getFont(context, fontAddress)
       textView.setPadding(
           post.postPadding[0].toPx(),
           post.postPadding[1].toPx(),
           post.postPadding[2].toPx(),
           post.postPadding[3].toPx()
       )
       textView.gravity = Gravity.CENTER
   }


    private fun locateTextView(
        index: Int,
        textView: TextView,
        post: Post
    ) {
        constraintSet.clear(textView.id, ConstraintSet.TOP)
        constraintSet.clear(textView.id, ConstraintSet.BOTTOM)
        val ind = index - 1
        val num0 = post.textLocation[0]
        /* if (ind == 0) {
           util.logi("DrawGeneral 241 inside locateTextView num0=$num0   postNum=${post.postNum}     padding=${post.postPadding.joinToString()}")
         }*/
        when (num0) {
            100 -> arangeText100(index, textView, post)
            101 -> arangeText101(index, textView, post)
            102 -> arangeText102(index, textView, post)
            10 -> arangeText10(index, textView, post)
            else -> oldFation(index, textView, post)
        }
    }


    private fun arangeText10(index: Int, textView: TextView, post: Post) {

//val arr= arrayListOf(10, NO_TOP,     35,         0,             0,        300,         2,          100)
        //top            //dis   //bottom       //line1   //dis1   //line2      //dis2        //locate in the
        //  10,    NO_TOP,  35,     10,                 0,        60,         1,            -30           //  bottom
        //  10,          100,             35,  NO_BOTTOM,        0,         0,          0,              0           // top
//         post.textLocation = arrayListOf(10, -1, 35,10, 1, 30, 0,0)
//         post.textLocation = arrayListOf(10, 10, 30,-1, 3, 30, 0,0)
        val line = index - 1
        val ind1 = post.lineNum - line - 1
        var top = post.textLocation[1]
        if (top!= NO_TOP){
            top=top.toPx()
        }
        val dis = post.textLocation[2].toPx()
        var bottom = post.textLocation[3]
        if (bottom!= NO_BOTTOM){
            bottom=bottom.toPx()
        }
        val line1 = post.textLocation[4]                                   // from this line
        val dis1 =  post.textLocation[5].toPx()
        val line2 = post.textLocation[6]                                // from this line
        val dis2 =  post.textLocation[7].toPx()
        var distanceBotton =bottom + dis * ind1
        var distanceTop =top + dis * line

        if (line==0) {
//              util.logi("------------------" )
//            util.logi("DrawGeneral 280 inside  arangeText102 top=$top dis=$dis bottom=$bottom line1=$line1 dis1=$dis1  line2=$line2 dis2=$dis2" )
        }
//       util.logi("DrawGeneral 282 line=$line   ind1=$ind1  distanceTop=$distanceTop      ${textView.text}")

        if (top == NO_TOP) {                                                                            //locate in the bottom
            if (line <= line1) {
                distanceBotton += dis1
            }
            if (line <= line2) {
                //    util.logi("DrawGeneral 290 line=$line   line2=$line2 ")
                distanceBotton += dis2
            }

            constraintSet.connect(
                textView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, distanceBotton
            )
        }

        if (bottom == NO_BOTTOM) {                                                                            //locate in the top
//            var distance = (top + (dis * line)).toPx()                                                //10, 0,              35,   NO_BOTTOM,     0,       500,         0,          0
//              util.logi("DrawGeneral 303  line=$line   line1=$line1 ")
            if (line >= line1) {
//                util.logi("DrawGeneral 305  line=$line   line1=$line1 ")
                distanceTop += dis1
            }
            if (line >=line2) {
                distanceTop += dis2
            }
            constraintSet.connect(
                textView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, distanceTop
            )
        }
    }











    /* private fun locateTextView(
         index: Int,
         textView: TextView,
         post: Post
     ) {
         constraintSet.clear(textView.id, ConstraintSet.TOP)
         constraintSet.clear(textView.id, ConstraintSet.BOTTOM)
         val ind = index - 1
         val num0 = post.textLocation[0]
         *//* if (ind == 0) {
           util.logi("DrawGeneral 241 inside locateTextView num0=$num0   postNum=${post.postNum}     padding=${post.postPadding.joinToString()}")
         }*//*
        when (num0) {
            100 -> arangeText100(index, textView, post)
            101 -> arangeText101(index, textView, post)
            102 -> arangeText102(index, textView, post)
            else -> oldFation(index, textView, post)
        }
    }*/

    private fun arangeText100(index: Int, textView: TextView, post: Post) {
//    post.postPadding = arrayListOf(100, -1, 30, 0)     // locate in the buttom
//    post.postPadding = arrayListOf(100, 58, 30,-1)     // locate in the top
        val line = index - 1
        val ind1 = post.lineNum - line - 1
        val top = post.textLocation[1]
        val dis = post.textLocation[2]
        val bottom = post.textLocation[3]
        if (top == -1) {                                           // locate in the buttom
            constraintSet.connect(
                textView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, (bottom + (dis * ind1)).toPx()
            )
        }

        if (bottom == -1) {                                    // locate in the top
            constraintSet.connect(
                textView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, (top + dis * line).toPx()
            )
        }
    }

    private fun arangeText101(index: Int, textView: TextView, post: Post) {
        //top     //dis        //bottom     //line1   //dis1
//       post.textLocation = arrayListOf(101, -1,       35,            10,             3,        60)            // locate in the bottom
        //     post.textLocation = arrayListOf(101, 10,       35,            -1,            3,      60)            //locate in the top

        val line = index - 1
        val ind1 = post.lineNum - line - 1
        val top = post.textLocation[1]
        val dis = post.textLocation[2]
        val bottom = post.textLocation[3]
        val line1 = post.textLocation[4]                                   // from this line
        val dis1 = dis + post.textLocation[5]                               //
        /*   if (line == 0) {
               util.logi("DrawGeneral 299  top=$top dis=$dis bottom=$bottom line1=$line1 dis1=$dis1 ")
               util.logi("-------------------")
           }
           util.logi("DrawGeneral 295  line=$line   ind1=$ind1       ${textView.text}")*/

        if (top == -1) {                                                                            // locate in the bottom
            var distance = (bottom + (dis * ind1)).toPx()
            if (line <= line1) {
                distance += dis1
            }
            constraintSet.connect(
                textView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, distance
            )
        }
        if (bottom == -1) {                                                                           //locate in the top
            var distance = (top + (dis * ind1)).toPx()
            if (line <= line1) {
                distance += dis1
            }
            constraintSet.connect(
                textView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, distance
            )
        }
    }

    private fun arangeText102(index: Int, textView: TextView, post: Post) {
        //top     //dis        //bottom     //line1   //dis1   //line2      //dis2
//        post.textLocation = arrayListOf(102,   -1,       35,            10,               0,        60,         1,            -30)            // locate in the bottom
        //      post.textLocation = arrayListOf(102,  10,       35,            -1,               0,         0,          0,              0)            //locate in the top

        val line = index - 1
        val ind1 = post.lineNum - line - 1
        val top = post.textLocation[1]
        val dis = post.textLocation[2]
        val bottom = post.textLocation[3]
        val line1 = post.textLocation[4]                                   // from this line
        val dis1 = dis + post.textLocation[5]
        val line2 = post.textLocation[6]                                   // from this line
        val dis2 = dis + post.textLocation[7]

//          if (line==0) {
//              util.logi("------------------" )
//            util.logi("DrawGeneral 295 inside  arangeText102 top=$top dis=$dis bottom=$bottom line1=$line1 dis1=$dis1  line2=$line2 dis2=$dis2" )
//          }
//       util.logi("DrawGeneral 295 line=$line   ind1=$ind1  distance=$distance      ${textView.text}")

        if (top == -1) {                                                                            //locate in the bottom
            var distance = (bottom + (dis * ind1)).toPx()
            if (line <= line1) {
                distance += dis1
            }
            if (line <= line2 && line>=line1 ) {
                distance += dis2
            }
            constraintSet.connect(
                textView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, distance
            )
        }

        if (bottom == -1) {                                                                            //locate in the top
            var distance = (top + (dis * line)).toPx()
            if (line <= line1) {
                distance += dis1
            }
            if (line <= line2 && line>=line1 ) {
                distance += dis2
            }
            constraintSet.connect(
                textView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, distance
            )
        }
    }

    private fun oldFation(index: Int, textView: TextView, post: Post) {
        val ind = index - 1
        if (post.postMargin[ind][3] == -1) {
            constraintSet.connect(
                textView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, post.postMargin[ind][1].toPx()
            )
        }
        if (post.postMargin[ind][1] == -1) {
            constraintSet.connect(
                textView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, post.postMargin[ind][3].toPx()
            )
        }
    }


   /* private fun locateTextView(
        index: Int,
        textView: TextView,
        post: Post
    ) {
        constraintSet.clear(textView.id, ConstraintSet.TOP)
        constraintSet.clear(textView.id, ConstraintSet.BOTTOM)
        val ind = index - 1
    //    logi("DrawGeneralPost 195  postMargin=${post.postMargin.joinToString()}")
     //   logi("DrawGeneralPost 196  index=$index   ind=$ind   post=$post")
        if (post.postMargin[ind][3] == -1) {
            constraintSet.connect(
                textView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, post.postMargin[ind][1].toPx()
            )
        }
        if (post.postMargin[ind][1] == -1) {
            constraintSet.connect(
                textView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, post.postMargin[ind][3].toPx()
            )
        }
    }*/

 /*  private fun locateTextView(
       index: Int,
       textView: TextView,
       post: Post
   ) {
       constraintSet.clear(textView.id, ConstraintSet.TOP)
       constraintSet.clear(textView.id, ConstraintSet.BOTTOM)
       val ind = index - 1
       val ind1=post.lineNum-ind-1
     //  logi("DrawGeneralPost  378  index=$index   ind=$ind       postNum=${post.postNum}")
       *//*         arrayListOf(  0,   -1 + di,   0,   90 + dd  ),
              arrayListOf(  0,   -1 + di,   0,   60 + dd  ),
              arrayListOf(  0,   -1 + di,   0,   30 + dd  ),
              arrayListOf(  0,   -1 + di,   0,    0 + dd  )
  *//*
//    post.postPadding = arrayListOf(100, -1, 30, 0)     // locate in the buttom
//    post.postPadding = arrayListOf(100, 58, 30,-1)     // locate in the top

       val num0=post.postPadding[0]
       val top=post.postPadding[1]
       val dis=post.postPadding[2]
       val bottom=post.postPadding[3]

     //  logi("DrawGeneral 391 num0=$num0  buttom=$bottom    dis=$dis   top=$top")

       if (num0==100) {
//         logi("DrawGeneral 393 buttom=$bottom")
//         if (post.postMargin[ind][1] == -1) {
         if (top == -1) {
//             logi("DrawGeneral 395 buttom=$bottom")
             constraintSet.connect(
                 textView.id,
                 ConstraintSet.BOTTOM,
                 ConstraintSet.PARENT_ID,
//                 ConstraintSet.BOTTOM, post.postMargin[ind][3].toPx()
                 ConstraintSet.BOTTOM, (bottom+dis*ind1).toPx()
             )
         }

         if (bottom == -1) {
             constraintSet.connect(
                 textView.id,
                 ConstraintSet.TOP,
                 ConstraintSet.PARENT_ID,
                 ConstraintSet.TOP, (top+dis*ind).toPx()
             )
         }

     }else{

         if (post.postMargin[ind][3] == -1) {
             constraintSet.connect(
                 textView.id,
                 ConstraintSet.TOP,
                 ConstraintSet.PARENT_ID,
                 ConstraintSet.TOP, post.postMargin[ind][1].toPx()
             )
         }
         if (post.postMargin[ind][1] == -1) {
             constraintSet.connect(
                 textView.id,
                 ConstraintSet.BOTTOM,
                 ConstraintSet.PARENT_ID,
                 ConstraintSet.BOTTOM, post.postMargin[ind][3].toPx()
             )
         }
     }
   }*/


}