package com.sg.alma55.interfaces

import com.sg.alma55.models.Comment


interface CommentsOptionClickListener {
    fun optionMenuClicked(comment: Comment)
}