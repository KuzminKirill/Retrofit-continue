package com.example.kirill.retrofittry.Activities

import android.content.Context
import android.content.Intent
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.course.view.*

import com.example.kirill.retrofittry.Parsers.Courses
import com.example.kirill.retrofittry.R

class Adapter(context : Context, obj : Courses) : BaseAdapter() {

    private fun ViewGroup.inflate(@LayoutRes layoutRes : Int, attachToRoot: Boolean = false) : View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    private var ctx = context
    private var obj = obj.courses


    /// Fill the layout with data
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view==null) {
            view = parent?.inflate(R.layout.course)
        }
        var p = obj[position]

        view!!.course_name.text = p.id.toString()
        view.course_description.text = p.description.toString()
        view.course_time.text = (256).toString()
        view.hyperlink.setOnClickListener { Toast.makeText(this.ctx,"course name = "
                + view.course_name.text
                + "\ndescription = "
                + view.course_description.text, Toast.LENGTH_SHORT).show() }
        view.setOnClickListener {  }

        view.setOnClickListener(View.OnClickListener {
            val i = Intent(ctx, CourseDetailActivityKotlin::class.java)
            i.putExtra("id",p.id.toString())
            startActivity(ctx,i,null)
        })

        return view
    }

    override fun getItem(position: Int): Any {
        return obj[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return obj.size
    }

}