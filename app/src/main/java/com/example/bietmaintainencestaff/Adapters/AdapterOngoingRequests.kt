package com.example.bietmaintainencestaff.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Modals.ModalPendingRequest

class AdapterOngoingRequests(val context: Context, private val listOngoingRequests:List<ModalPendingRequest>):RecyclerView.Adapter<AdapterOngoingRequests.HolderOngoingRequests>() {
    class HolderOngoingRequests (view: View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderOngoingRequests {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HolderOngoingRequests, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listOngoingRequests.size
    }
}