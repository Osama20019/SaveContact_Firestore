package com.osamaaltawil.savecontact_firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
var db:FirebaseFirestore= FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.title="Contacts"
        //array
        val data=ArrayList<DataClass>()
        //recycler view inflate
        rc_contact.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        try {
            //get data
            db.collection("Contacts").get().addOnSuccessListener {collection->
                //check if empty
                if (!collection.isEmpty){
                    data.addAll(collection.toObjects(DataClass::class.java))
                    //add data in array and set adapter
                    val adapter=RC_Adapter(this,data)
                    rc_contact.adapter=adapter
                    progress_bar.visibility=View.GONE
                }else{
                    progress_bar.visibility=View.GONE
                    tv_no_data.visibility=View.VISIBLE
                    //Toast.makeText(this,"no data",Toast.LENGTH_LONG).show()
                }

            }.addOnFailureListener {
                progress_bar.visibility=View.GONE
                tv_no_data.visibility=View.VISIBLE
                Log.e("getContact",it.message.toString())
            }
        }catch (e:Exception){}






    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.btn_addMenu){
            this.finish()
            startActivity(Intent(this,Add_contact::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
