package com.example.bietmaintainencestaff.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Modals.ModalPendingRequest
import com.example.bietmaintainencestaff.R
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AdapterCompletedRequests(
    val context: Context,
    private val listCompletedRequest: List<ModalPendingRequest>
) : RecyclerView.Adapter<AdapterCompletedRequests.HolderCompletedRequests>() {
    class HolderCompletedRequests(view: View) : RecyclerView.ViewHolder(view) {
        val reqId: TextView = view.findViewById(R.id.ReqId)
        val hostelName: TextView = view.findViewById(R.id.hostelName)
        val requestedBy: TextView = view.findViewById(R.id.name)
        val roomNo: TextView = view.findViewById(R.id.textView5)
        val status: TextView = view.findViewById(R.id.status)
        val requestTime: TextView = view.findViewById(R.id.timeOfRequest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderCompletedRequests {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pending_single, parent, false)
        return HolderCompletedRequests(view)
    }

    override fun onBindViewHolder(holder: HolderCompletedRequests, position: Int) {
        val request = listCompletedRequest[position]
        holder.reqId.text = "RQ" + request.requestId
        holder.hostelName.text = request.hostelName
        holder.requestedBy.text = request.requestedBy
        holder.roomNo.text = "Room No.-" + request.roomNo
        holder.status.text = request.status
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy,hh:mm a")
            val date = Date(request.requestId.toLong())
            val formattedDate = sdf.format(date)
            holder.requestTime.text = formattedDate
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return listCompletedRequest.size
    }

}