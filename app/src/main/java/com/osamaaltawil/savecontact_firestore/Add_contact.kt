package com.osamaaltawil.savecontact_firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_contact.*

class Add_contact : AppCompatActivity() {

    lateinit var dialog_builder:AlertDialog.Builder
    lateinit var dialog_inflator:View
    val db:FirebaseFirestore= FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        this.supportActionBar!!.title="Add Contact"

        dialog_builder= AlertDialog.Builder(this,R.style.AlertDialog)
         dialog_inflator=LayoutInflater.from(this).inflate(R.layout.progress_dialog,null)

         btn_save.setOnClickListener {
             //Get data from editText
             val name=ed_name.text.toString()
             val phone=ed_phoneNumber.text.toString()
             val address=ed_address.text.toString()

             //Check if data Not equal Null
             if (name!=""){
               if (phone!=""){
                   if (address!=""){

                       //add Contact data in Hash Map
                       val data=HashMap<String,Any>()

                       data["Name"]=name
                       data["PhoneNumber"]=phone
                       data["Address"]=address
                       show_dialog()

                       //add data to firestore
                       db.collection("Contacts").add(data)
                           .addOnSuccessListener {
                               dismiss_dialog()
                               this.finish()
                              startActivity(Intent(this,MainActivity::class.java))

                           }
                           .addOnFailureListener {
                               Log.e("AddContact",it.message.toString())
                               dismiss_dialog()
                               this.finish()
                               startActivity(Intent(this,MainActivity::class.java))


                           }
                   }else{
                       message("Please enter the Address")
                   }
               }  else{
                   message("Please enter Phone Number")
               }
             }else{
                 message("Please enter the name ")
             }
         }
    }
    fun message(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    fun show_dialog(){
        dialog_builder.setView(dialog_inflator)
        dialog_builder.setCancelable(false)
        dialog_builder.create().show()
    }
    fun dismiss_dialog(){
        dialog_builder.create().dismiss()

    }
}
