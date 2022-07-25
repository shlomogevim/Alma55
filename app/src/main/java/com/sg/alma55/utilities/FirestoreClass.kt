package com.sg.alma55.utilities

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sg.alma55.activities.MainActivity
import com.sg.alma55.activities.SplashActivity
import com.sg.alma55.modeles.User
import com.sg.alma55.utilities.Constants.USER_REF
import java.util.HashMap

class FirestoreClass:BaseActivity(){

    private val mFirestore= FirebaseFirestore.getInstance()

   /* fun registerUser(activity: RegisterActivity, userInfo: User) {
        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        mFirestore.collection(USER_REF)
            // Document ID for users fields. Here the document it is the User ID.
            .document(userInfo.uid)
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                // Here call a function of base activity for transferring the result to it.
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }*/

    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        // logi("FirebaseClass 53       currentUser=$currentUser")
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
    /*   private fun saveUserName() {
        var currentUserID = FirestoreClass().getCurrentUserID()
      //  logi("SplashActivity 69    currentUserID=$currentUserID")
//currentUserID=""

        if (currentUserID !="") {
            //  FirestoreClass().getUserDetails(this)
            FirebaseFirestore.getInstance().collection(USER_REF).document(currentUserID)
                .get()
                .addOnSuccessListener { document ->
                    val user = document.toObject(User::class.java)!!
                    currentUser=user
                    currentUseName=user.userName
                    pref.edit().putString(SHARPREF_CURRENT_USER_NAME,"${user.userName}").apply()
                }
        }else{
            pref.edit().putString(SHARPREF_CURRENT_USER_NAME,"אורח").apply()
        }
    }*/

    fun getUserDetails(activity: Activity) {
        // logi("FirebaseClass 61")
        var currentId=getCurrentUserID()
        // logi("FirebaseClass 63       currentId=$currentId")
        // currentId=""
        if (currentId.isNotEmpty()) {
            mFirestore.collection(USER_REF).document(getCurrentUserID())
                .get()
                .addOnSuccessListener { document ->
                    //Log.i(activity.javaClass.simpleName, document.toString())
                    // Here we have received the document snapshot which is converted into the User Data model object.
                    val user = document.toObject(User::class.java)!!

//                    insertToSharedPref(activity, user)             // for what ??
                    //     logi("FirebaseClass 73")
                    when (activity) {
                        is SplashActivity -> {
                            activity.getingUserData(user)
                        }
                        is MainActivity -> {
                            activity.getCurrentUser(user)
                        }
//                        is LoginActivity -> {
//                            activity.userLoggedInSuccess(user)
//                        }
//                        is PostDetailesActivity -> {
//                            activity.getUserNameSetting(user)
//                        }
//                        is PostSettingActivity -> {
//                            activity.getUserNameSetting(user)
//                        }
//                        is UserProfileActivity -> {
//                            activity.setUserFromFirebaseClassToUserProfileActivity(user)
//                        }
//
//                        is SetupActivity -> {
//                            activity.getingUserData(user)
//                        }
//                        is HelpActivity -> {
//                            activity.getingUserData(user)
//                        }
//                        is HowToActivity -> {
//                            activity.getingUserData(user)
//                        }
                    }
                }

//                .addOnFailureListener { e ->
//                    when (activity) {
//                        is LoginActivity -> {
//                            activity.hideProgressDialog()
//                        }
//                        is PostSettingActivity -> {
//                            activity.hideProgressDialog()
//                        }
//                    }
//
//                    Log.e(
//                        activity.javaClass.simpleName,
//                        "Error while getting user details.",
//                        e
//                    )
//                }
        }
    }

  /*  private fun insertToSharedPref(activity: Activity, user: User) {
        val sharedPreferences = activity.getSharedPreferences(
            MYSHOPPAL_PREFERENCES,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(
            LOGGED_IN_USERNAME,
            "${user.userName} ${user.lastName}"
        )
        editor.apply()
    }*/

  /*  fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
        // Collection Name
        mFirestore.collection(USER_REF).document(getCurrentUserID()).update(userHashMap)
            .addOnSuccessListener {
                when (activity) {
                    is UserProfileActivity -> {
                        // Call a function of base activity for transferring the result to it.
                        activity.userProfileUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener { e ->

                when (activity) {
                    is UserProfileActivity -> {
                        // Hide the progress dialog if there is any error. And print the error in log.
                        activity.hideProgressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    "Error while updating the user details.",
                    e
                )
            }
    }*/

  /*  fun uploadImageToCloudStorage(activity: Activity, imageFileURI: Uri?) {
        //getting the storage reference
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            USER_PROFILE_IMAGE + System.currentTimeMillis() + "."
                    + getFileExtension( activity, imageFileURI )
        )
        //adding the file to reference
        sRef.putFile(imageFileURI!!)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                Log.e("gg", taskSnapshot.metadata!!.reference!!.downloadUrl.toString() )
                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("Downloadable Image URL", uri.toString())
                        // Here call a function of base activity for transferring the result to it.
                        when (activity) {
                            is UserProfileActivity -> {
                                activity.imageUploadSuccess(uri.toString())
                            }
                        }
                    }
            }
            .addOnFailureListener { exception ->

                // Hide the progress dialog if there is any error. And print the error in log.
                when (activity) {
                    is UserProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception
                )
            }
    }*/




}