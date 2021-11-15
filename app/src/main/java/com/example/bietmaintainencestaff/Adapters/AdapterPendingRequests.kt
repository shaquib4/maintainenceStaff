package com.example.bietmaintainencestaff.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Modals.ModalPendingRequest
import com.example.bietmaintainencestaff.R

class AdapterPendingRequests(val context: Context,private val listPendingRequests:List<ModalPendingRequest>):RecyclerView.Adapter<AdapterPendingRequests.HolderPendingRequests>() {
    class HolderPendingRequests(view: View) : RecyclerView.ViewHolder(view) {
        val reqId:TextView=view.findViewById(R.id.ReqId)
        val hostelName:TextView=view.findViewById(R.id.hostelName)
        val requestedBy:TextView=view.findViewById(R.id.name)
        val roomNo:TextView=view.findViewById(R.id.textView5)
        val status:TextView=view.findViewById(R.id.status)
        val requestTime:TextView=view.findViewById(R.id.timeOfRequest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPendingRequests {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.pending_single,parent,false)
        return HolderPendingRequests(view)
    }

    override fun onBindViewHolder(holder: HolderPendingRequests, position: Int) {
        val request=listPendingRequests[position]
        holder.reqId.text="RQ"+request.requestId
    }

    override fun getItemCount(): Int {
       return listPendingRequests.size
    }
}