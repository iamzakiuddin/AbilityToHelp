package com.androidapp.abilitytohelp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.adapter.ConversationAdapter
import com.androidapp.abilitytohelp.model.ConvoResult
import com.androidapp.abilitytohelp.network.NetworkResources
import com.androidapp.abilitytohelp.viewmodels.FunActivityViewModel

class BasicConversationActivity : AppCompatActivity() {

    private var convoAdapter: ConversationAdapter? = null
    private var back: ImageView? = null
    private var conversationList: RecyclerView? = null
    val funActivityViewModel: FunActivityViewModel by viewModels()
    var basicConvoDataList = ArrayList<ConvoResult>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_conversation)
        supportActionBar?.hide()

        back = findViewById(R.id.close)
        conversationList = findViewById(R.id.dataList)
        conversationList?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        convoAdapter = ConversationAdapter(basicConvoDataList)
        conversationList?.adapter = convoAdapter

        back?.setOnClickListener {
            onBackPressed()
        }

        funActivityViewModel.getBasicConversationList()
        funActivityViewModel.getBasicConversationListObserver().observe(this, Observer { it->
            when (it.status) {
                NetworkResources.NetworkStatus.LOADING -> {

                }

                NetworkResources.NetworkStatus.ERROR -> {
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }

                NetworkResources.NetworkStatus.SUCCESS -> {
                    basicConvoDataList.clear()
                    it.data?.results?.let { it1 -> basicConvoDataList.addAll(it1) }
                    convoAdapter?.notifyDataSetChanged()
                }
                else -> {
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun hideKeyboardNow() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}