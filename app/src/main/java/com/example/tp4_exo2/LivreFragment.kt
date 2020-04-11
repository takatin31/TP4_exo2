package com.example.tp4_exo2

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_livre.*


class LivreFragment : Fragment() {
    lateinit var livre: Livre
    var exit : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        livre = arguments?.getSerializable("livre") as Livre
        exit = arguments!!.getBoolean("exit")
        return inflater.inflate(R.layout.fragment_livre, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleView.text = livre.titre
        autherView.text = livre.autheur
        resumeView.text = livre.resume
        val drawableResId = activity!!.resIdByName(livre.photo, "drawable")
        imageView.setImageResource(drawableResId)

        if (!exit){
            val v = sortirBtnView
            (v.parent as ViewManager).removeView(sortirBtnView)
        }else {
            sortirBtnView.setOnClickListener {
                val transaction = activity!!.supportFragmentManager.beginTransaction()
                transaction.remove(this)
                transaction.commit()
            }
        }
    }

    fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }

}
