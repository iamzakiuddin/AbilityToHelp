package com.androidapp.abilitytohelp.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.adapter.SynonymAdapter

class AntonymsFragment : Fragment() {

    var antonymsListView: RecyclerView? = null
    var antonymsAdapter : SynonymAdapter? = null
    var antonymsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_antonyms, container, false)
        antonymsListView = view.findViewById(R.id.antonymsListView)
        if (arguments!=null){
            if (arguments?.getStringArrayList("antonymsList")!=null){
                antonymsList = arguments?.getStringArrayList("antonymsList") as ArrayList<String>
            }
        }
        antonymsAdapter = SynonymAdapter(antonymsList)
        antonymsListView?.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        antonymsListView?.adapter = antonymsAdapter
        return view
    }

    companion object {
        fun newInstance(antonymsList: ArrayList<String>): AntonymsFragment {
            val fragment = AntonymsFragment()
            val args = Bundle()
            args.putStringArrayList("antonymsList", antonymsList)
            fragment.arguments = args
            return fragment
        }
    }

    fun notifyData(){
        antonymsAdapter?.notifyDataSetChanged()
    }
}