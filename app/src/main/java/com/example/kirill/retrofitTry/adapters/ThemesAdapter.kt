package com.example.kirill.retrofitTry.adapters

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.example.kirill.retrofitTry.Parsers.Themes
import com.example.kirill.retrofitTry.R
import kotlinx.android.synthetic.main.theme.view.*

class ThemesAdapter(context : Context, obj : Themes) : BaseAdapter() {

    private var ctx = context
    private var obj = obj.themes

    /*
     *Fill the layout with data
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view==null) {
            view = parent?.inflate(R.layout.theme)
        }
        val p = obj[position]

        view!!.theme_name.text = p.id.toString()
        view.theme_description.text = p.description.toString()
        view.theory.text = p.theory.toString()
        view.course_moreInfo.setOnClickListener { Toast.makeText(this.ctx,"theme name = "
                + view.theme_name.text
                + "\ndescription = "
                + view.theme_description.text, Toast.LENGTH_SHORT).show() }
/*
        view.setOnClickListener(View.OnClickListener {
            val i = Intent(ctx, CourseDetailActivity::class.java)
            i.putExtra("id",p.id.toString())
            startActivity(ctx,i,null)
        })
*/
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