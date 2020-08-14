package com.mahmoudrefaie.sekkawahda.ui.Profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import com.mahmoudrefaie.sekkawahda.Network.RetrofitClient
import com.mahmoudrefaie.sekkawahda.R
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class EditCarImageBottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {

    private var bottomSheetListener : BottomSheetListener ?= null

    private lateinit var editedImage : ImageView
    private lateinit var selectImage : ImageView
    private lateinit var cancel : Button
    private lateinit var save : Button

    private var appAuth: SharedPreferences? = null
    private var accessToken: String? = null

    private var carImage : String? = null
    private var imageUri : Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.carimage_bottom_sheet_dialog,container,false)

        appAuth = activity?.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
        accessToken = appAuth?.getString("TOKEN", null)

        editedImage = v.findViewById(R.id.car_image)
        selectImage = v.findViewById(R.id.select_img)
        cancel= v.findViewById(R.id.cancel_btn)
        save = v.findViewById(R.id.save_btn)

        carImage  = this.arguments?.getString("car_Image_URL")

        selectImage.setOnClickListener(this)
        cancel.setOnClickListener(this)
        save.setOnClickListener (this)
        Picasso.get()
                .load(carImage)
                .placeholder(R.drawable.ic_baseline_directions_car_white)
                .into(editedImage)

        return v
    }
    override fun onClick(p0: View?) {
        if(view?.id == R.id.select_img){
            pickImageFromGallery()
        }
        else if(view?.id == R.id.cancel_btn){
            dismiss()
        }
        else if(view?.id == R.id.save_btn){
            if(!validateSelectedImage())
                return
            bottomSheetListener?.onEditPhoneButtonClicked(imageUri!!)
            dismiss()
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT)
        gallery.addCategory(Intent.CATEGORY_OPENABLE)
        gallery.type = "image/*"
        startActivityForResult(gallery, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == EditCarImageBottomSheetDialog.IMAGE_PICK_CODE && data != null) {
            imageUri = data.data   //This is imageUri which we use to get path of image , but it didn't give us the real path
            editedImage.setImageURI(imageUri)

        }
    }

    interface BottomSheetListener{
        fun onEditPhoneButtonClicked(imageUri : Uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetListener = context as BottomSheetListener
        }catch (e : ClassCastException){
            throw ClassCastException(context.toString() + " must implement BottomSheetListener")
        }
    }

    private fun validateSelectedImage(): Boolean {
        return if (imageUri != null) {
            true
        } else {
            Toast.makeText(context,"Select image",Toast.LENGTH_LONG).show()
            false
        }
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }
}