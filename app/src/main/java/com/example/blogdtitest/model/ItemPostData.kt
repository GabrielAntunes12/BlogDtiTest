package com.example.blogdtitest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ItemPostData(
    var title: String?,
    var description: String?,
    var id: Int? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
) : Serializable

