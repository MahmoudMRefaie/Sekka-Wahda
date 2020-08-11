package com.mahmoudrefaie.sekkawahda.ui.UserProfile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mahmoudrefaie.sekkawahda.R
import java.lang.ClassCastException

class EditUsernameBottomSheetDialog : BottomSheetDialogFragment() {

    private var bottomSheetListener : BottomSheetListener ?= null

    private lateinit var editUsername : TextInputLayout
    private lateinit var cancel : Button
    private lateinit var save : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.username_bottom_sheet_dialog,container,false)

        editUsername = v.findViewById(R.id.username)
        cancel= v.findViewById(R.id.cancel_btn)
        save = v.findViewById(R.id.save_btn)

        val username : String? = this.arguments?.getString("username")
        editUsername.editText?.setText(username)
        editUsername.isFocusable = true
        editUsername.requestFocus()
        editUsername.editText?.isSelected = true

        cancel?.setOnClickListener(View.OnClickListener {
            dismiss()
        })

        save?.setOnClickListener (View.OnClickListener {
            val editedUsername = editUsername?.editText?.text.toString()
            bottomSheetListener?.onButtonClicked(editedUsername)
            dismiss()
        })


        /*
        //City Spinner
        val city : Spinner? = v.findViewById(R.id.spinner_dialog)
        val save : Button? = v.findViewById(R.id.save_btn)
        val cancel : Button? = v.findViewById(R.id.cancel_btn)

        val adapter = ArrayAdapter.createFromResource(this,
                R.array.city_spinner, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        city?.setAdapter(adapter)

//        city?.getSelectedItemPosition()
//        city.setSelection()
//        val selectedCity = city?.selectedItem.toString()

        cancel?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {

            }
        })
        save?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {

            }

        })

        */

        return v
    }

    interface BottomSheetListener{
        fun onButtonClicked(text : String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetListener = context as BottomSheetListener
        }catch (e : ClassCastException){
            throw ClassCastException(context.toString() + " must implement BottomSheetListener")
        }
    }
}