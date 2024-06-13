package apimocked

import android.content.SharedPreferences
import com.example.blogdtitest.model.ItemPostData
import com.example.blogdtitest.util.LIST_MOCK
import com.example.blogdtitest.util.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class ApiMocked(private val sharedPreferences: SharedPreferences) {
    fun getPosts(): List<ItemPostData>? {
        return try {
            val value = sharedPreferences.getString(LIST_MOCK, null)
            Gson().fromJson(value, object : TypeToken<List<ItemPostData>>() {}.type)
        } catch (e: Exception) {
            null
        }
    }

    fun publishPost(itemPostData: ItemPostData): Boolean {
        return try {
            val list = formatList(itemPostData)
            val putList = Gson().toJson(list)
            sharedPreferences.edit().putString(LIST_MOCK, putList).apply()
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun formatList(itemPostData: ItemPostData): MutableList<ItemPostData> {
        val list = getPosts()
        return if (!list.isNullOrEmpty()) {
            val manipulateList = list.toMutableList()
            manipulateList.add(formatApiInfos(itemPostData,manipulateList.last().id))
            manipulateList
        } else {
            val manipulateCreateList = mutableListOf<ItemPostData>()
            manipulateCreateList.add(formatApiInfos(itemPostData, 0))
            manipulateCreateList
        }
    }

    private fun formatApiInfos(itemPostData: ItemPostData, id: Int?): ItemPostData {
        val idN = id ?: 0
        itemPostData.id = idN + 1
        itemPostData.createdAt = Utils.getHour(Date())
        return itemPostData
    }
}