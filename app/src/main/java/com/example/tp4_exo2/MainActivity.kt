package com.example.tp4_exo2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    var listeLivre : MutableList<Livre> = ArrayList()

    lateinit var adapter: LivreAdapter
    lateinit var layoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLivres()

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = LivreAdapter(this)
        recyclerView.adapter = adapter
    }

    fun initLivres(){
        var livre1 = Livre("Livre1", "Auteur1", "photo1", "resume1 resume resume resume reusme reusm, ceci est un resume donc pour resume")
        var livre2 = Livre("Livre2", "Auteur2", "ein", "resume2 resume resume resume reusme reusm, ceci est un resume donc pour resume")
        var livre3 = Livre("Livre3", "Auteur3", "emir", "resume3 resume resume resume reusme reusm, ceci est un resume donc pour resume")
        var livre4 = Livre("Livre4", "Auteur4", "ibn_badis", "resume4 resume resume resume reusme reusm, ceci est un resume donc pour resume")
        var livre5 = Livre("Livre5", "Auteur5", "newt", "resume5 resume resume resume reusme reusm, ceci est un resume donc pour resume")
        var livre6 = Livre("Livre6", "Auteur6", "photo1", "resume6 resume resume resume reusme reusm, ceci est un resume donc pour resume")
        var livre7 = Livre("Livre7", "Auteur7", "ein", "resume7 resume resume resume reusme reusm, ceci est un resume donc pour resume")

        listeLivre.add(livre1)
        listeLivre.add(livre2)
        listeLivre.add(livre3)
        listeLivre.add(livre4)
        listeLivre.add(livre5)
        listeLivre.add(livre6)
        listeLivre.add(livre7)
    }


    class LivreAdapter(val activity : MainActivity) : RecyclerView.Adapter<LivreAdapter.TachViewHolder>(){
        class TachViewHolder(v : View) : RecyclerView.ViewHolder(v){
            val livreView = v.findViewById<TextView>(R.id.livreView)
            val livreLayout = v.findViewById<RelativeLayout>(R.id.livreLayout)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TachViewHolder {
            return TachViewHolder(LayoutInflater.from(activity).inflate(R.layout.livre_layout, parent, false))
        }

        override fun getItemCount(): Int {
            return activity.listeLivre.size
        }

        override fun onBindViewHolder(holder: TachViewHolder, position: Int) {
            holder.livreView.text = activity.listeLivre[position].titre
            val isLandscape = activity.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE

            if (isLandscape){
                holder.livreLayout.setOnClickListener {

                    val bundle = Bundle()
                    bundle.putBoolean("exit",false)
                    bundle.putInt("livreIndex",position)
                    bundle.putSerializable("livre", activity.listeLivre[position] as Serializable)

                    val transaction = activity.supportFragmentManager.beginTransaction()
                    val modFrag = LivreFragment()

                    modFrag.arguments = bundle
                    transaction.replace(R.id.frameLayout, modFrag)
                    transaction.addToBackStack(null)
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    transaction.commit()


                }
            }else{
                holder.livreLayout.setOnClickListener {



                    val bundle = Bundle()
                    bundle.putInt("livreIndex",position)
                    bundle.putBoolean("exit",true)
                    bundle.putSerializable("livre", activity.listeLivre[position] as Serializable)

                    val transaction = activity.supportFragmentManager.beginTransaction()
                    val frag2 = LivreFragment()
                    frag2.arguments = bundle

                    transaction.replace(R.id.frameLayout, frag2)
                    transaction.addToBackStack(null)
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    transaction.commit()
                }
            }

        }
    }
}
