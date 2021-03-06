package com.example.kirill.retrofitTry.adapters

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

import com.example.kirill.retrofitTry.Parsers.Courses
import com.example.kirill.retrofitTry.R
import com.example.kirill.retrofitTry.activities.ThemesActivity

class CoursesAdapter(context : Context, obj : Courses) : BaseAdapter() {

    private var ctx = context
    private var obj = obj.courses

    /*
     *Fill the layout with data
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view==null) {
            view = parent?.inflate(R.layout.course)
        }
        val p = obj[position]

        view!!.course_name.text = p.id.toString()
        view.course_name.text = p.name.toString()
        view.course_description.text = p.description.toString()
        view.course_time.text = (256).toString()
        view.course_moreInfo.setOnClickListener { Toast.makeText(this.ctx,"course name = "
                + view.course_name.text
                + "\ndescription = "
                + view.course_description.text, Toast.LENGTH_SHORT).show() }
        view.setOnClickListener {  }

        view.setOnClickListener(View.OnClickListener {
            val i = Intent(ctx, ThemesActivity::class.java)
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

    private fun ViewGroup.inflate(@LayoutRes layoutRes : Int, attachToRoot: Boolean = false) : View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}