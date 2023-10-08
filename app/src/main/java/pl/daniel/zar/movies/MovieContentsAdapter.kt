package pl.daniel.zar.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.daniel.services.data.Movie
import pl.daniel.zar.R
import pl.daniel.zar.databinding.MovieItemBinding

class MovieContentsAdapter(
    private val onItemSelected: (Movie) -> Unit
) : ListAdapter<Movie, MovieContentsAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.item = item
            binding.executePendingBindings()
            binding.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    if (item.favorite) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24
                )
            )
            binding.mcvMovie.setOnClickListener { onItemSelected(item) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            if (oldItem.hashCode() != newItem.hashCode()) return false
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

}