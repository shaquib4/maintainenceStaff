package com.example.bietmaintainencestaff.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Modals.ModalStaff
import com.example.bietmaintainencestaff.R

class AdapterAllStaff(val context: Context,private  val arrayList: ArrayList<ModalStaff>):RecyclerView.Adapter<AdapterAllStaff.AllStaff>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAllStaff.AllStaff {
        TODO("Not yet implemented")
    }

    class AllStaff(view: View):RecyclerView.ViewHolder(view) {
       val name:TextView=view.findViewById(R.id.staff_nm)
        val phone:TextView=view.findViewById(R.id.staff_no)
    }

    override fun onBindViewHolder(holder: AdapterAllStaff.AllStaff, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
      return arrayList.size
    }
}