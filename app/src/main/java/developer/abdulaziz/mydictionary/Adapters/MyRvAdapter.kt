package developer.abdulaziz.mydictionary.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.mydictionary.Room.Entity.MyRoom
import developer.abdulaziz.mydictionary.databinding.ItemRvBinding

class MyRvAdapter(
    private val list: ArrayList<MyRoom>,
    private val myClick: MyClick
) :
    RecyclerView.Adapter<MyRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: MyRoom, position: Int) {
            binding.apply {
                english.text = user.english
                uzbek.text = user.uzbek
                im.visibility = View.VISIBLE
                root.setOnClickListener { myClick.onClick(user, position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(hol: ViewHolder, pos: Int) = hol.onBind(list[pos], pos)
    override fun getItemCount(): Int = list.size
    interface MyClick {
        fun onClick(my: MyRoom, pos: Int)
    }
}