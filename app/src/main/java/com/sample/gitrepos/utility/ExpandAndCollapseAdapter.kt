package com.sample.gitrepos.utility

import android.view.View
import com.sample.gitrepos.R

class ExpandAndCollapseAdapter<T>(list: List<T>, private val onRowClick: ((Int)->Unit)?) : GenericAdapter<T>(list) {

    private var mExpandedPosition = -1

    override fun onBindViewHolder(holder: GenericViewHolder<*>, position: Int) {

        super.onBindViewHolder(holder, position)


        val actionVGroup = holder.getBinding()!!.root.findViewById<View>(R.id.container_layout)

        if (actionVGroup != null) {

            val expandViewGroup = holder.getBinding()!!.root.findViewById<View>(R.id.info_container_layout)
            val actionVGroup = holder.getBinding()!!.root.findViewById<View>(R.id.container_layout)

            if (mExpandedPosition == position) {
                expandViewGroup.visibility = View.VISIBLE
            } else {
                expandViewGroup.visibility = View.GONE
            }

            if (onRowClick != null) {
                actionVGroup.setOnClickListener {
                    onRowClick.invoke(position-1)
                }
            } else {
                actionVGroup.setOnClickListener {
                    if (expandViewGroup.visibility == View.VISIBLE) {
                        mExpandedPosition = -1
                        expandViewGroup.visibility = View.GONE
                    } else {
                        mExpandedPosition = position
                        expandViewGroup.visibility = View.VISIBLE
                    }
                    notifyDataSetChanged()
                }
            }
        }
    }

}
