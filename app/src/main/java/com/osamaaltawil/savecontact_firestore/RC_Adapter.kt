package com.osamaaltawil.savecontact_firestore

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rc_layout.view.*

class RC_Adapter(var activity: Activity, var data: ArrayList<DataClass>) :
    RecyclerView.Adapter<RC_Adapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.tv_name
        val phone = itemView.tv_phone
        val address = itemView.tv_address
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.rc_layout, parent, false)
        return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.setText("Name: " + data[position].Name)
        holder.phone.setText("PhoneNumber: " + data[position].PhoneNumber)
        holder.address.setText("Address: " + data[position].Address)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}


