package com.saned.view.ui.adapter.dynamicWF

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saned.model.HAData
import com.saned.R
import com.saned.view.ui.activities.dynamicWF.HistoryDynamicWFActivity
import kotlinx.android.synthetic.main.empty_placeholder_item.view.*
import kotlinx.android.synthetic.main.dynamic_wf_list_item.view.*


class DynamicWFHistoryAdapter(private val dataList: List<HAData>, val context: Context,
                              val activity: HistoryDynamicWFActivity
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemFlag: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: RecyclerView.ViewHolder
        if (itemFlag == 0){
            view =
                EmptyHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.empty_placeholder_item, parent, false)
                )
        }else {
            view = ViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.dynamic_wf_list_item, parent, false)
            )
        }
        return view
    }

    override fun getItemCount(): Int {
        itemFlag = dataList.size
        return if (dataList.size > 0) dataList.size else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(itemFlag != 0){
            val viewHolder: ViewHolder = (holder as ViewHolder)



            viewHolder.itemVal1!!.text = "" + dataList[position].noofmonths
            viewHolder.itemVal2!!.text = "" + dataList[position].reason

        }else if(itemFlag == 0) {
            val emptyViewHolder: EmptyHolder = (holder as EmptyHolder)

//            emptyViewHolder.emptyImage.setImageResource(R.drawable.ic_notification)
            emptyViewHolder.emptyText.text = "No Data Found"

        }

    }

    internal inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val itemVal1 = itemView.labelVal1
        val itemVal2 = itemView.labelVal2

        init {
            itemView.setOnClickListener {
               activity.onListItemClicked(dataList[adapterPosition],adapterPosition)
            }
        }
    }

    class EmptyHolder(view : View) : RecyclerView.ViewHolder(view){
        val emptyImage = itemView.empty_imageView
        val emptyText = itemView.empty_textView
    }

    interface ListAdapterListener{
        fun onListItemClicked(dummyData: HAData, position: Int)
    }



}