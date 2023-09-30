package pl.daniel.zar.movies

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pl.daniel.services.data.Movie
import pl.daniel.zar.R
import pl.daniel.zar.databinding.MovieItemBinding

class MovieContentsAdapter(
    private val context: Context,
    private val onItemSelected: (Movie) -> Unit
) :
    RecyclerView.Adapter<MovieContentsAdapter.ViewHolder>() {

    private var values: MutableList<Movie> = mutableListOf()
    private var idFavorite = 0L

    inner class ViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.item = item
            binding.executePendingBindings()
            binding.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    if (item.id == idFavorite) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24
                )
            )
            binding.mcvMovie.setOnClickListener { onItemSelected(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(values[position])

    fun addItems(newItems: List<Movie>) {
        val startPosition = values.size
        if (!values.containsAll(newItems)) {
            values.addAll(newItems)
            notifyItemRangeInserted(startPosition, newItems.size)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<Movie>, favorite: Long) {
        idFavorite = favorite
        values.clear()
        values.addAll(newItems)
        notifyDataSetChanged()
    }
}