package com.livermor.dumchev.delegateadapters.base

import androidx.annotation.DrawableRes

data class ImageItem(val title: String, @DrawableRes val imageRes: Int)

data class CheckItem(val title: String, var isChecked: Boolean)

data class TextItem(val title: String, val description: String)
