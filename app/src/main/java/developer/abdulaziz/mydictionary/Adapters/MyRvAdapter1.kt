package developer.abdulaziz.mydictionary.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.mydictionary.databinding.ItemRv1Binding

class MyRvAdapter1(
    private val list: ArrayList<String>,
    private val myPopup: MyPopup
) :
    RecyclerView.Adapter<MyRvAdapter1.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRv1Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: String, position: Int) {
            binding.name.text = user
            binding.popup.setOnClickListener { myPopup.onClick(it, user, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRv1Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(hol: ViewHolder, pos: Int) = hol.onBind(list[pos], pos)
    override fun getItemCount(): Int = list.size
    interface MyPopup {
        fun onClick(view: View, user: String, position: Int)
    }
}