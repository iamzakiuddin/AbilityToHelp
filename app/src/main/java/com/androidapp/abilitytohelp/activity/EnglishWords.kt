package com.androidapp.abilitytohelp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.adapter.EnglishAdapter
import com.androidapp.abilitytohelp.utils.Utils

class EnglishWords : AppCompatActivity() {

    var searchField: EditText? = null
    var searchBtn: Button? = null
    var back: LinearLayout? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null
    var recyclerView: RecyclerView? = null
    var adapter: EnglishAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_english_words)

        supportActionBar?.hide()
        back = findViewById(R.id.backBtn)
        llAdView = findViewById(R.id.llAdView)
        recyclerView = findViewById(R.id.englishWordsListView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
        adapter = EnglishAdapter(sentencesData)
        recyclerView?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        recyclerView?.adapter = adapter

        back?.setOnClickListener {
            onBackPressed()
        }


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


    val sentencesData = hashMapOf(
        "be" to "Will you be my friend?",
        "and" to "You and I will always be friends.",
        "of" to "Today is the first of November.",
        "a" to "I saw a bear today.",
        "in" to "She is in her room.",
        "to" to "Let’s go to the park.",
        "have" to "I have a few questions.",
        "too" to "I like her too.",
        "it" to "It is sunny outside.",
        "I" to "I really like it here.",
        "that" to "That door is open.",
        "for" to "This letter is for you.",
        "you" to "You are really nice.",
        "he" to "He is my brother.",
        "with" to "I want to go with you.",
        "on" to "I watch movies on my iPad.",
        "do" to "What will you do now?",
        "say" to "Can I say something?",
        "this" to "This is my favorite cookie.",
        "they" to "They are here!",
        "at" to "Can you pick me up at the mall?",
        "but" to "I’m sorry but she’s away.",
        "we" to "We are going to watch a movie.",
        "his" to "This is his box.",
        "from" to "This card came from my cousin.",
        "not" to "That’s not what I want.",
        "can’t" to "I can’t open it.",
        "won’t" to "I won’t open it.",
        "by" to "Will you come by and see me?",
        "she" to "She is very happy.",
        "or" to "Do you like blue or yellow?",
        "what" to "What are you thinking of?",
        "go" to "I want to go there.",
        "their" to "This is their house.",
        "can" to "What can I do for you?",
        "who" to "Who can help me?",
        "get" to "Can you get me my eyeglasses?",
        "if" to "What if I fail?",
        "would" to "Would you help me out?",
        "her" to "I have her book.",
        "all" to "All my favorite books are on this shelf.",
        "my" to "My mom is coming to visit.",
        "make" to "Can we make our projects together?",
        "about" to "What is this movie about?",
        "know" to "Do you know where this place is?",
        "will" to "I will help you find that place.",
        "up" to "I live up in the mountains.",
        "time" to "There was a time I liked to play golf.",
        "there" to "There are so many things I want to learn.",
        "year" to "This is the year I’m finally going to learn English.",
        "so" to "I am so sorry.",
        "think" to "I think I need to lie down.",
        "when" to "When will I see you again?",
        "which" to "Which of these slippers are yours?",
        "them" to "Please give this to them.",
        "some" to "Please give them some of the apples I brought home.",
        "me" to "Can you give me some apples?",
        "people" to "There are so many people at the mall today.",
        "take" to "Please take home some of these apples",
        "out" to "Please throw the trash out.",
        "into" to "My puppy ran into the woods.",
        "just" to "Just close your eyes.",
        "see" to "Did you see that?",
        "him" to "I heard him singing earlier.",
        "your" to "Your mom is here.",
        "come" to "Can your mom and dad come to the party?",
        "could" to "Could you help me with my project?",
        "now" to "I want to watch this now.",
        "than" to "I like this cake better than the other one you showed me.",
        "like" to "I like this bag better than the other one you showed me.",
        "other" to "I like these shoes better than the other ones you showed me.",
        "how" to "How do I turn this on?",
        "then" to "We had breakfast and then we went to church.",
        "its" to "I need to read its manual.",
        "our" to "This is our home now.",
        "two" to "Two cheeseburgers, please.",
        "more" to "Can I have some more milk shake?",
        "these" to "Do you like these ribbons?",
        "want" to "Do you want these ribbons?",
        "way" to "Can you look this way?",
        "look" to "Please look this way.",
        "first" to "She was my very first teacher.",
        "also" to "She was also my best friend.",
        "new" to "I have new shoes.",
        "because" to "I am crying because I’m sad.",
        "day" to "Today is National Friendship day.",
        "use" to "How do I use this?",
        "no" to "There’s no electricity now.",
        "man" to "There’s a man outside looking for you.",
        "find" to "Where can I find rare furniture?",
        "here" to "My mom is here.",
        "thing" to "One thing led to another.",
        "give" to "Give her these pearls.",
        "many" to "We shared many dreams together.",
        "well" to "You know me so well.",
        "only" to "You are my only friend here.",
        "those" to "Those boots belong to my friend.",
        "tell" to "Can you tell me which way to go?",
        "one" to "She’s the one he’s been waiting for.",
        "very" to "I’m very upset right now.",
        "even" to "She can’t even stand on her own.",
        "back" to "I’ll be right back.",
        "any" to "Have you had any luck on your research?",
        "good" to "You’re a good person.",
        "woman" to "That woman looks so polished.",
        "through" to "Your faith will see you through tough times.",
        "us" to "Do you want to go with us?",
        "life" to "This is the best day of my life.",
        "child" to "I just saw a child cross the street by herself.",
        "work" to "I have to go to work.",
        "down" to "Let’s go down.",
        "may" to "You may take your seats.",
        "after" to "Let’s have dinner after work.",
        "should" to "Should I buy this dress?",
        "call" to "Call me when you get home, okay?",
        "world" to "I want to travel and see the world.",
        "over" to "I can’t wait for this day to be over.",
        "school" to "My cousin goes to school here.",
        "still" to "I still think you should go.",
        "try" to "Can you try to be nicer to him?",
        "as" to "What’s in that box?",
        "last" to "This is my last slice of cake, I promise!",
        "ask" to "Can you ask the waiter to bring us some wine?"
    )

}