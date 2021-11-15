package com.example.bietmaintainencestaff.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Modals.ModalPendingRequest

class AdapterCompletedRequests(val context: Context,private val listCompletedRequest:List<ModalPendingRequest>):RecyclerView.Adapter<AdapterCompletedRequests.HolderCompletedRequests>() {
    class HolderCompletedRequests(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderCompletedRequests {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HolderCompletedRequests, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listCompletedRequest.size
    }

}