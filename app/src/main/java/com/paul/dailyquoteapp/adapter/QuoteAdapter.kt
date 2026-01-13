import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paul.dailyquoteapp.databinding.ItemQuoteBinding
import com.paul.dailyquoteapp.model.QuoteModel

class QuoteAdapter(
    private val quoteList: List<QuoteModel>,
    private val onShareClick: (QuoteModel) -> Unit
) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    inner class QuoteViewHolder(val binding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemQuoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return QuoteViewHolder(binding)
    }

    override fun getItemCount(): Int = quoteList.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = quoteList[position]

        holder.binding.tvQuote.text = item.text
        holder.binding.tvAuthor.text = "- ${item.author}"

        holder.binding.btnShare.setOnClickListener {
            onShareClick(item)   // 🔥 pass clicked quote
        }
    }
}
