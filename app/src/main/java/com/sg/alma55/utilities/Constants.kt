package com.sg.alma55.utilities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap

/**
 * A custom object to declare all the constant values in a single file. The constant values declared here is can be used in whole application.
 */
object Constants {


    const val IMAGE: String = "image"
    const val COMPLETE_PROFILE: String = "profileCompleted"
    const val MYSHOPPAL_PREFERENCES: String = "MyShopPalPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 3
    const val MALE: String = "Male"
    const val FEMALE: String = "Female"
    const val MOBILE: String = "mobile"
    const val GENDER: String = "gender"
    const val USER_PROFILE_IMAGE: String = "User_Profile_Image"

    //---------------------------------------------------------------
    const val SHAR_PREF = "char_pref"
    const val CURRENT_USER_EXIST = "current_user_exist"
    const val FIRESTORE_USER_ID="firststore_user_id"
    const val EXIST = "exist"
    const val NOT_EXIST = "not_exist"
    const val CONSTANT = "constant"
    const val NOT_CONSTANT = "notConstant"
    const val POST_PICTURE = "Posts Picture"
    const val POST_REF = "Posts"
    const val POST_ID = "post_id"
    const val POST_NUM = "post_num"
    const val POST_LINE_NUM = "post_lineNum"
    const val POST_IMAGE_URI = "post_image_uri"
    const val POST_TEXT = "post_text"
    const val POST_MARGIN = "post_margin"
    const val POST_BACKGROUND = "post_background"
    const val POST_TRANPARECY = "post_tranparecy"
    const val POST_TEXT_SIZE = "post_textSize"
    const val POST_PADDING = "post_padding"
    const val POST_TEXT_LOCATION="post_text_location"
    const val POST_TEXT_COLOR = "post_textColor"
    const val POST_FONT_FAMILY = "post_fontFamily"
    const val POST_RADIUS = "post_radius"
    const val POST_TIME_STAMP = "post_time_stamp"
    const val POST_LINE_SPACING = "post_line_spacing"
    const val POST_RECOMMENDED = "post_recommended"

    const val POST_REF_STAM = "Posts Stam"
    const val POST_IMAGE_STAM = "Posts Image Stam"

    const val TRUE="true"
    const val FALSE="false"
    const val NO_TOP= -1
    const val NO_BOTTOM= -1

   // const val SHARPREF_POST_NUM = "ShareprefPostNum"
    const val SHARPREF_ALMA = "ShareprefAlma"
    const val SHARPREF_CURRENT_POST_NUM = "ShareprefCurrentPostNum"
    const val SHARPREF_CURRENT_POST_POSITION = "ShareprefCurrentPostPOSITION"
    const val SHARPREF_TOTAL_POSTS_SIZE = "ShareprefTotalPostSize"
    const val SHARPREF_POSTS_ARRAY="ShearePrefPostsArray"
    const val SHARPREF_SORT_SYSTEM="SharprefSortSystem"
    const val SHARPREF_SORT_BY_GRADE="SharprefSortByGrade"
    const val SHARPREF_SORT_BY_TIME_PUBLISH="SharprefSortByTimePublish"
    const val SHARPREF_SORT_BY_RECOMMENDED="SharprefSortByRecommended"
    const val SHARPREF_SPLASH_SCREEN_DELAY="SharprefSplashScreenDelay"
    const val SHARPREF_GRADE_ZERO="SharprefGradeZero"
    const val SHARPREF_GRADE_ARRAY="SharprefGradeArray"
    const val SHARPREF_MOVING_BACKGROUND="SharprefMovingBackground"


    const val SHARPREF_COMMENTS_ARRAY="ShearePrefCommentsArray"
    const val SHARPREF_CURRENT_POST = "ShareprefCurrentPost"
    const val SHARPREF_CURRENT_POST_NUM_STRING = "ShareprefCurrentPostNumString"

    const val SHARPREF_CURRENT_USER_NAME = "ShareprefCurrentUserName"
    const val SHARPREF_NO_USER = "ShareprefNoUser"




    const val POST_EXSTRA="post_exstra"
    const val USER_EXTRA="user_exstra_two"

//    const val USER_REF = "users"
    const val USER_REF = "New users"

    const val USERNAME = "userName"
    const val LASTNAME = "lastName"
    const val USER_EMAIL = "email"
    const val USER_GENDER = "gender"
    const val USER_MOTO = "moto"
    const val USER_IMAGE = "image"
    const val USER_TIME = "time"
    const val USER_PASSWORD = "userPassword"
    const val USER_ID = "userId"
    const val USER_GRADE_AR="userGradeAr"

    const val USER_BIO = "user bio"

    const val USER_EXSRTA = "user_exstra"
    const val USER_USERNAMEEXSRTA = "user_usernameexstra"
    const val USER_FULLNAME = "full name1"
    const val USER_USERNAME = "user name1"

    const val COMMENT_REF = "Comments"
  //  const val NEW_COMMENT_REF = "New Comments"
    const val COMMENT_LIST = "Comment List"
    const val COMMENT_TEXT = "comment_text"
    const val COMMENT_USER_NAME = "comment_user_name"
    const val COMMENT_USER_ID = "comment_user_id"
    const val COMMENT_POST_NUM_STRING = "comment_post_num_string"
    const val COMMENT_ID = "comment_id"
    const val COMMEND_TIME_STAMP = "comment_time_stamp"
    const val COMMEND_INDEX = "comment_index"

    const val DIALOG_EXSTRA = "dealogexstra"
    const val HELP_EXPLANATION_INDEX="helpExplationIndex"
    const val ARTICLES_DETALES_INDEX="articlesDitalesIndex"

    const val ARTICAL_MODE="articalMode"
    const val ARTICAL_REF="Articles"
    const val ARTICAL_NUM="articalNum"
    const val ARTICAL_TEXT="articalText"
    const val ARTICAL_TEXT_COLOR="articalTextColor"
    const val ARTICAL_TEXT_SIZE="articalTextSize"
    const val ARTICAL_TITLE="articalTitle"
    const val ARTICAL_BACKGROUND="articalBackGround"
    const val ARTICAL_FONT_FAMILY="articalFontFamily"
    const val ARTICAL_TIMESTAMP="articalTimestamp"
   const val SHARPREF_ARTICLRS_ARRAY="sharprefArticulesArray"



    fun showImageChooser(activity: Activity) {
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI  )
        // Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }


    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        /*
         * MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
         *
         * getSingleton(): Get the singleton instance of MimeTypeMap.
         *
         * getExtensionFromMimeType: Return the registered extension for the given MIME type.
         *
         * contentResolver.getType: Return the MIME type of the given content URL.
         */
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }

}