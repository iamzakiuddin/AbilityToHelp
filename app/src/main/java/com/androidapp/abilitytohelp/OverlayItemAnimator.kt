package com.androidapp.abilitytohelp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.adapter.ConversationAdapter

class OverlayItemAnimator : DefaultItemAnimator() {

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        if (oldHolder is ConversationAdapter.ConvoItemHolder && newHolder is ConversationAdapter.ConvoItemHolder) {
            val oldOverlay = oldHolder.overlay
            val newOverlay = newHolder.overlay

            val overlayAnimator = ObjectAnimator.ofFloat(oldOverlay, "alpha", 1f, 0f)
            overlayAnimator.duration = 300

            val moveAnimator = ObjectAnimator.ofFloat(newOverlay, "alpha", 0f, 1f)
            moveAnimator.duration = 300

            AnimatorSet().apply {
                playSequentially(overlayAnimator, moveAnimator)
                start()
            }
        }

        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY)
    }
}
