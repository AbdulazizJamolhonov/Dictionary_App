package developer.abdulaziz.mydictionary.Object

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom

object MyObject {
    var myRoom = MyRoom()
    var list = ArrayList<MyRoom>()
    private lateinit var sp: SharedPreferences

    fun init(c: Context) {
        sp = c.getSharedPreferences("name", Context.MODE_PRIVATE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var sharedList: ArrayList<String>
        get() = gsonStringToList(sp.getString("keyList", "[]")!!)
        set(value) = sp.edit {
            it.putString("keyList", listToGsonString(value))
        }

    private fun gsonStringToList(gsonString: String): ArrayList<String> =
        Gson().fromJson(gsonString, object : TypeToken<ArrayList<String>>() {}.type)

    private fun listToGsonString(list: ArrayList<String>): String = Gson().toJson(list)
}