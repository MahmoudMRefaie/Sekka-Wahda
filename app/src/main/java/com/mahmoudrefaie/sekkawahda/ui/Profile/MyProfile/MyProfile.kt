package com.mahmoudrefaie.sekkawahda.ui.Profile.MyProfile

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
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.R
import com.mahmoudrefaie.sekkawahda.ui.Login.Login
import com.mahmoudrefaie.sekkawahda.ui.Profile.EditBottomSheetDialogs.*
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
import java.lang.Exception

class MyProfile : AppCompatActivity(), View.OnClickListener, EditUsernameBottomSheetDialog.BottomSheetListener,
                    EditEmailBottomSheetDialog.BottomSheetListener, EditPhoneBottomSheetDialog.BottomSheetListener,
                    EditCityBottomSheetDialog.BottomSheetListener, EditCarModelBottomSheetDialog.BottomSheetListener,
                    EditDriverLicenseBottomSheetDialog.BottomSheetListener, EditCarLicenseBottomSheetDialog.BottomSheetListener{
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    @BindView(R.id.show_image)
    lateinit var showProfilePic: CircleImageView
    @BindView(R.id.username)
    lateinit var username: TextView
    @BindView(R.id.ratingBar)
    lateinit var ratingBar: RatingBar
    @BindView(R.id.edit_username)
    lateinit var editUsername: ImageView
    @BindView(R.id.city_result)
    lateinit var city: TextView
    @BindView(R.id.edit_city)
    lateinit var editCity: ImageView
    @BindView(R.id.email_result)
    lateinit var email: TextView
    @BindView(R.id.edit_email)
    lateinit var editEmail: ImageView
    @BindView(R.id.phone_result)
    lateinit var phoneNumber: TextView
    @BindView(R.id.edit_phone)
    lateinit var editPhone: ImageView
    @BindView(R.id.ssn_result)
    lateinit var ssn: TextView
    @BindView(R.id.car_image_img)
    lateinit var carImage: ImageView
    @BindView(R.id.edit_car_image)
    lateinit var editCarImage: ImageView
    @BindView(R.id.driver_license)
    lateinit var driverLicenseLayout: RelativeLayout
    @BindView(R.id.driver_license_result)
    lateinit var driverLicense: TextView
    @BindView(R.id.edit_driver_license)
    lateinit var editDriverLicense: ImageView
    @BindView(R.id.car_license_result)
    lateinit var carLicense: TextView
    @BindView(R.id.edit_car_license)
    lateinit var editCarLicense: ImageView
    @BindView(R.id.car_model_result)
    lateinit var carModel: TextView
    @BindView(R.id.edit_car_model)
    lateinit var editCarModel: ImageView
    @BindView(R.id.profile_progress_bar)
    lateinit var profileProgressBar: ProgressBar

    private var accessToken: String? = null

    private var sharedPre: SharedPreferences? = null
    private var appAuth: SharedPreferences? = null

    private var userId: Int? = 0

    lateinit var myProfileViewModel : MyProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        ButterKnife.bind(this)

        toolbar.setTitle("")
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.proj_main_color))
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)          //BackArraw at ToolBar
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_blue_24dp)

        sharedPre = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        appAuth = getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)
        userId = appAuth?.getInt("userId",userId!!)

        Log.e("ID ",userId.toString())

        myProfileViewModel = ViewModelProviders.of(this).get(MyProfileViewModel::class.java)    //using MVVM
        myProfileViewModel.getProfileData(userId!!,accessToken!!)                                      //to get profile data
        getProfileDataObserver()

        showProfilePic.setOnClickListener(this)
        editUsername.setOnClickListener(this)
        editCity.setOnClickListener(this)
        editEmail.setOnClickListener(this)
        editPhone.setOnClickListener(this)
        editCarImage.setOnClickListener(this)
        editDriverLicense.setOnClickListener(this)
        editCarLicense.setOnClickListener(this)
        editCarModel.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.show_image) {
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
                    pickImageFromGallery(IMAGE_PICK_CODE)
                }
            } else {
                //System OS < Marshmallow
                pickImageFromGallery(IMAGE_PICK_CODE)
            }
        }
        else if(view?.id == R.id.edit_username){
            editUsernameBottomDialog()
        }
        else if(view?.id == R.id.edit_city){
            editCityBottomDialog()
        }
        else if(view?.id == R.id.edit_email){
            editEmailBottomDialog()
        }
        else if(view?.id == R.id.edit_phone){
            editPhoneNumberBottomDialog()
        }
        else if(view?.id == R.id.edit_car_model){
            editCarModelBottomDialog()
        }
        else if(view?.id == R.id.edit_car_image){
            pickImageFromGallery(CAR_IMAGE_PICK_CODE)
        }
        else if(view?.id == R.id.edit_car_license){
            editCarLicenseBottomDialog()
        }
        else if(view?.id == R.id.edit_driver_license){
            editDriverLicenseBottomDialog()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editUsernameBottomDialog(){
        val usernameBottomSheetDialog = EditUsernameBottomSheetDialog()
        usernameBottomSheetDialog.show(supportFragmentManager,"EditUsernameBottomSheetDialog")
        val bundle = Bundle()
        bundle.putString("username",username.text.toString())
        usernameBottomSheetDialog.arguments = bundle
    }
    override fun onEditUsernameButtonClicked(text: String) {
        username.text = text
    }

    private fun editCityBottomDialog() {
        val cityBottomSheetDialog = EditCityBottomSheetDialog()
        cityBottomSheetDialog.show(supportFragmentManager,"EditCityBottomSheetDialog")
        val bundle = Bundle()
        bundle.putString("city",city.text.toString())
        cityBottomSheetDialog.arguments = bundle
    }
    override fun onEditCityButtonClicked(text: String) {
        city.text = text
    }

    private fun editEmailBottomDialog() {
        val emailBottomSheetDialog = EditEmailBottomSheetDialog()
        emailBottomSheetDialog.show(supportFragmentManager,"EditEmailBottomSheetDialog")
        val bundle = Bundle()
        bundle.putString("email",email.text.toString())
        emailBottomSheetDialog.arguments = bundle
    }
    override fun onEditEmailButtonClicked(text: String) {
        email.text = text
    }

    private fun editPhoneNumberBottomDialog(){
        val phoneBottomSheetDialog = EditPhoneBottomSheetDialog()
        phoneBottomSheetDialog.show(supportFragmentManager,"EditPhoneBottomSheetDialog")
        val bundle = Bundle()
        bundle.putString("phone",phoneNumber.text.toString())
        phoneBottomSheetDialog.arguments = bundle
    }
    override fun onEditPhoneButtonClicked(text: String) {
        phoneNumber.text = text
    }

    private fun editCarModelBottomDialog() {
        val carModelBottomSheetDialog = EditCarModelBottomSheetDialog()
        carModelBottomSheetDialog.show(supportFragmentManager,"EditCarModelBottomSheetDialog")
        val bundle = Bundle()
        bundle.putString("car_model",carModel.text.toString())
        carModelBottomSheetDialog.arguments = bundle
    }
    override fun onEditCarModelButtonClicked(text: String) {
        carModel.text = text
    }
    private fun uploadCarImage(carImagePart: MultipartBody.Part, token: String) {
        val call : Call<String>? = RetrofitClient.instance?.api?.updateProfileCarImage(carImagePart , "Bearer $token")
        call?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(this@MyProfile, "Check your connection", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("onResponse ", response.body().toString())
                Toast.makeText(this@MyProfile, "Image Uploaded", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun editDriverLicenseBottomDialog() {
        val driverLicenseBottomSheetDialog = EditDriverLicenseBottomSheetDialog()
        driverLicenseBottomSheetDialog.show(supportFragmentManager,"EditDriverLicenseBottomSheetDialog")
        val bundle = Bundle()
        bundle.putString("driver_license",driverLicense.text.toString())
        driverLicenseBottomSheetDialog.arguments = bundle
    }
    override fun onEditDriverLicenseButtonClicked(text: String) {
        driverLicense.text = text
    }

    private fun editCarLicenseBottomDialog() {
        val carLicenseBottomSheetDialog = EditCarLicenseBottomSheetDialog()
        carLicenseBottomSheetDialog.show(supportFragmentManager,"EditCarLicenseBottomSheetDialog")
        val bundle = Bundle()
        bundle.putString("car_license",carLicense.text.toString())
        carLicenseBottomSheetDialog.arguments = bundle
    }
    override fun onEditCarLicenseButtonClicked(text: String) {
        carLicense.text = text
    }

    private fun getProfilePicObserver(){
        myProfileViewModel.profilePic.observe(this, object : Observer<String>{
            override fun onChanged(t: String?) {
                var imageName = t
                imageName = imageName?.replace("~/","")
                val imageUrl = "https://seka.azurewebsites.net/$imageName"
                Log.e("OnResponse pic : ", imageUrl)
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.blank_profile_pic)
                        .into(showProfilePic, object : com.squareup.picasso.Callback{
                            override fun onSuccess() {
                            }
                            override fun onError(e: Exception?) {
                                showProfilePic.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.blank_profile_pic))
                            }
                        })
            }
        })
    }

    private fun getProfileDataObserver(){
        getProfilePicObserver()
        myProfileViewModel.fullName.observe(this, Observer<String> { t -> username.text = t })
        myProfileViewModel.driverTotalRate.observe(this, Observer<Float> { t -> ratingBar.rating = t!! })
        myProfileViewModel.city.observe(this, Observer<String> { t -> city.text = t })
        myProfileViewModel.email.observe(this, Observer<String> { t -> email.text = t })
        myProfileViewModel.phoneNumber.observe(this, Observer<String> { t -> phoneNumber.text = t })
        myProfileViewModel.ssn.observe(this, Observer<String> { t -> ssn.text = t })
        myProfileViewModel.driverLicense.observe(this, Observer<String> {
            driverLicense.text = it
            /*if(driverLicense.text.toString().equals("") or(driverLicense.text == null) ){
                //Create ImageView to editing driverLicense if driver text is empty or null
                val addDriverLicense = ImageView(applicationContext)
                addDriverLicense.setImageResource(R.drawable.edit_pin)
                val relativeParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
                relativeParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
                relativeParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                relativeParams.addRule(RelativeLayout.BELOW, R.id.header_car_image);
                relativeParams.setMargins(0,0,20,0)
                addDriverLicense.layoutParams = relativeParams
                driverLicenseLayout.addView(addDriverLicense)
                addDriverLicense.setOnClickListener(View.OnClickListener {
                    editDriverLicenseBottomDialog()
                })
            }else
                driverLicense.text = it*/
        })
        myProfileViewModel.carLicense.observe(this, Observer<String> { t -> carLicense.text = t })
        myProfileViewModel.carModel.observe(this, Observer<String> { t -> carModel.text = t })
        myProfileViewModel.carImageUrl.observe(this, Observer<String> { t ->
            val carImageUrlResponse = t?.replace("~/", "")
            val imageUrl = "https://seka.azurewebsites.net/$carImageUrlResponse"
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_baseline_directions_car)
                    .into(carImage)
            profileProgressBar.visibility = View.INVISIBLE //hide profile details progress bar
        })
    }

    private fun pickImageFromGallery(selectedImageStatic : Int) {
        //Intent to pick image
        val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT)
        gallery.addCategory(Intent.CATEGORY_OPENABLE)
        gallery.type = "image/*"
        startActivityForResult(gallery,selectedImageStatic)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission from popup granted
                    pickImageFromGallery(IMAGE_PICK_CODE)
                   } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE && data != null && data.getData() != null) {
            val imageUri = data.data   //This is imageUri which we use to get path of image , but it didn't give us the real path
            showProfilePic.setImageURI(imageUri)
            try {
                val imagePartProfile = uriToMultipart(imageUri)
                uploadProfilePic(imagePartProfile , accessToken!!)
            } catch (e: IOException) {
                e.message
            }
        }
        else if (resultCode == Activity.RESULT_OK && requestCode == CAR_IMAGE_PICK_CODE && data != null) {
            val imageUri = data.data   //This is imageUri which we use to get path of image , but it didn't give us the real path
            carImage.setImageURI(imageUri)
            try {
                val carImagePart = uriToMultipart(imageUri)
                uploadCarImage(carImagePart, accessToken!!)
            } catch (e: IOException) {
                e.message
            }
        }
    }

    //this step to convert imageUri to Multipart and send it to API directly
    private fun uriToMultipart(fileUri : Uri?) : MultipartBody.Part {
        //Log.e("RealPath ", getRealPathFromUri(fileUri!!)) //this function to get real path from Uri
        //val realPath = getRealPathFromUri(fileUri)!!
        Log.e("RealPath ", RealPathUtil.getRealPath(this,fileUri)!!)
        val realPath = RealPathUtil.getRealPath(this,fileUri)!!
        val file = File(realPath)
        val fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)  //convert file to multipart
        val filePart = MultipartBody.Part.createFormData("image", file.name, fileBody)
        return filePart
    }

    private fun uploadProfilePic(imageFile : MultipartBody.Part , token : String){
        val call : Call<String>? = RetrofitClient.instance?.api?.addProfilePic( imageFile , "Bearer $token" )
        call?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(this@MyProfile, "Check your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("onResponse ", response.body().toString())
                Toast.makeText(this@MyProfile, "Image Uploaded", Toast.LENGTH_LONG).show()
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
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
        private val CAR_IMAGE_PICK_CODE = 2002
    }

}