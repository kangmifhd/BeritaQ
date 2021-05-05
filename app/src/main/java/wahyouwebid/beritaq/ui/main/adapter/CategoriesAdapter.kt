package wahyouwebid.beritaq.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wahyouwebid.beritaq.data.model.categories.Categories
import wahyouwebid.beritaq.databinding.AdapterCategoryBinding
import wahyouwebid.beritaq.utils.Utils.getDrawableIdFromFileName

class CategoriesAdapter (
    private val showDetail: (Categories) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var data = ArrayList<Categories>()

    fun setData(itemList: List<Categories>?) {
        if (itemList.isNullOrEmpty()) return
        data.clear()
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]
            tvTitle.text = item.title
            imgThumbnail.setImageResource(getDrawableIdFromFileName(
                    imgThumbnail.context,
                    item.icon!!
            ))

            root.setBackgroundResource(getDrawableIdFromFileName(
                    imgThumbnail.context,
                    item.color!!
            ))
            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterCategoryBinding) : RecyclerView.ViewHolder(view.root)

}