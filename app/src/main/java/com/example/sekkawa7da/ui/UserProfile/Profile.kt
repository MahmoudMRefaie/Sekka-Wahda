package com.example.sekkawa7da.ui.UserProfile

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sekkawa7da.Api.RetrofitClient
import com.example.sekkawa7da.R
import com.example.sekkawa7da.ui.Login
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException

class Profile : AppCompatActivity(), View.OnClickListener {
    @BindView(R.id.show_image)
    lateinit var showProfilePic: CircleImageView
    @BindView(R.id.take_image)
    lateinit var takeImage: ImageButton
    @BindView(R.id.logout)
    lateinit var logOut: Button
    @BindView(R.id.progress)
    lateinit var progressBar: ProgressBar

    private var imageUri: Uri? = null
    private var accessToken: String? = null

    private var sharedPre: SharedPreferences? = null
    private var appAuth: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        ButterKnife.bind(this)

        sharedPre = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        appAuth = getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        takeImage?.setOnClickListener(this)
        logOut?.setOnClickListener(this)

        loadingProfilepic()

    }

    private fun loadingProfilepic() {
        val call : Call<String>? = RetrofitClient.getInstance().api.getProfilePic("Bearer $accessToken")
        call?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String?>, t: Throwable) {
                //Toast.makeText(this@Profile, "Check your connection", Toast.LENGTH_LONG).show()
                Toast.makeText(applicationContext,"OnFailure : "+t.message,Toast.LENGTH_LONG).show()
                Log.e("onFailure : ",t.message)
                progressBar!!.setVisibility(View.INVISIBLE)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    progressBar!!.setVisibility(View.INVISIBLE)
                    var imageName  = response.body()
                    imageName = imageName?.replace("~/","")
                    val imageUrl = "https://seka.azurewebsites.net/$imageName"
                    Log.e("OnResponse : ", imageName)
                    showImage(imageUrl)
                }else{
                    Log.e("OnResponse : ","else")
                    Toast.makeText(applicationContext,"ERROR",Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    private fun showImage(imageUrl : String?) {
        Log.e("showImage ", imageUrl)
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.blank_profile_pic)
                .into(showProfilePic)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.take_image) {
            //Check runtime permission
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    //Permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show pop up to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //System OS < Marshmallow
                pickImageFromGallery()
            }
        }
        else if(view?.id == R.id.logout) {
            val editor: SharedPreferences.Editor = sharedPre?.edit()!!
            Log.e("Log Out" , sharedPre?.getBoolean("logged",true).toString())
            editor.remove("logged")
            editor.commit()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
//        val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT)
//        gallery.addCategory(Intent.CATEGORY_OPENABLE)
//        gallery.type = "image/*"
//        //gallery.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(gallery, IMAGE_PICK_CODE)
        val getIntent = Intent(Intent.ACTION_GET_CONTENT)
        getIntent.type = "image/*"

        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = "image/*"

        val chooserIntent = Intent.createChooser(getIntent, "Select Image")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

        startActivityForResult(chooserIntent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission from popup granted
                    pickImageFromGallery()
                   } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE && data != null) {
            imageUri = data?.data   //This is imageUri which we use to get path of image , but it didn't give us the real path
            showProfilePic?.setImageURI(imageUri)
            try {
                val imagePart = uriToMultipart(imageUri)
                uploadProfilePic(imagePart , "Bearer $accessToken")
            } catch (e: IOException) {
                e.message
            }
        }
    }

    //this step to convert imageUri to Multipart and send it to API directly
    private fun uriToMultipart(fileUri : Uri?) : MultipartBody.Part {
        Log.e("RealPath ", getRealPathFromUri(fileUri!!)) //this function to get real path from Uri
        val realPath = getRealPathFromUri(fileUri)
        val file = File(realPath)
        val fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)  //convert file to multipart
        val filePart = MultipartBody.Part.createFormData("image", file.name, fileBody)
        return filePart
    }

    private fun uploadProfilePic(imageFile : MultipartBody.Part , token : String){
        val call : Call<String>? = RetrofitClient.getInstance().api.addProfilePic( imageFile , token )
        call?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(this@Profile, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("onResponse ", response.body().toString())
                Toast.makeText(this@Profile, "Image Uploaded", Toast.LENGTH_LONG).show()
            }
        })
    }

    //This function which convert from Uri to real path (this is save my life :D )
    fun getRealPathFromUri(uri: Uri): String? {
        Log.e("getRealPathFromUri ","getRealPathFromUri function")
        // DocumentProvider
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(this, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                var contentUri: Uri? = null
                val id: String = DocumentsContract.getDocumentId(uri)
                contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
                return getDataColumn(this, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(
                        split[1]
                )
                return getDataColumn(this, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {

            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(this, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    private fun getDataColumn(context: Context, uri: Uri?, selection: String?,
                              selectionArgs: Array<String>?): String? {
        Log.e("getDataColumn ","getDataColumn function")
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs,
                    null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        }
        finally {
            cursor?.close()
        }
        return null
    }

    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    private fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }

}