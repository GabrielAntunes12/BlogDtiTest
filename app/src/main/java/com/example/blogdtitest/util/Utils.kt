package com.example.blogdtitest.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

const val DETAILS_ITEM_POST_DATA = "details_item_post_data"
const val API_DECIDED_REST = "api_decided_REST"
const val LIST_MOCK = "list_mock"

object Utils {
     fun getHour(date : Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
        return dateFormat.format(date)
    }
}