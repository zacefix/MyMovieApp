package pl.daniel.zar.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import pl.daniel.zar.R
import pl.daniel.zar.databinding.FragmentDescriptionBinding
import pl.daniel.zar.utils.RoundedTransformation

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setData(args.movie)
        setupObservables()
        observerClickToFavorite()
    }

    private fun observerClickToFavorite() {
        binding.fabFavoriteMovie.setOnClickListener { viewModel.clickFabFavorite() }
    }

    private fun setupObservables() {
        viewModel.data.observe(viewLifecycleOwner) {
            with(binding) {
                tvTittleMovie.text = it.originalTitle
                tvDetails.text = it.overview
                tvPremiere.text = resources.getString(R.string.premiere, it.releaseDate)
                tvRating.text = resources.getString(R.string.rating, it.voteAverage.toString())
                loadPicture(it.posterPath)
            }
        }

        viewModel.isFavorite.asLiveData().observe(viewLifecycleOwner) { favorite ->
            binding.fabFavoriteMovie.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (!favorite) R.drawable.baseline_star_border_24 else R.drawable.baseline_star_24
                )
            )
        }
    }

    private fun loadPicture(image: String?) {
        Picasso.get()
            .load(URL_IMAGE + image)
            .transform(RoundedTransformation(50, 4))
            .error(R.drawable.baseline_broken_image_24)
            .into(binding.ivMovie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val URL_IMAGE = "https://image.tmdb.org/t/p/original/"
    }
}