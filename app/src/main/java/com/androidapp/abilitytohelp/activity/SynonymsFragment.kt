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

class SynonymsFragment : Fragment() {

    var synonymsListView: RecyclerView? = null
    var synonymsAdapter : SynonymAdapter? = null
    var synonymsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_synonyms, container, false)
        synonymsListView = view.findViewById(R.id.synonymsListView)
        if (arguments!=null){
            if (arguments?.getStringArrayList("synonymsList")!=null){
                synonymsList = arguments?.getStringArrayList("synonymsList") as ArrayList<String>
            }
        }
        synonymsAdapter = SynonymAdapter(synonymsList)
        synonymsListView?.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        synonymsListView?.adapter = synonymsAdapter
        return view
    }

    companion object {
        fun newInstance(synonymsList: ArrayList<String>): SynonymsFragment {
            val fragment = SynonymsFragment()
            val args = Bundle()
            args.putStringArrayList("synonymsList", synonymsList)
            fragment.arguments = args
            return fragment
        }
    }

    fun notifyData(){
        synonymsAdapter?.notifyDataSetChanged()
    }

}