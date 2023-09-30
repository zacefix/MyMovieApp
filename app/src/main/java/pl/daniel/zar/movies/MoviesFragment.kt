package pl.daniel.zar.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pl.daniel.zar.base.BaseFragment
import pl.daniel.zar.databinding.FragmentMoviesBinding

@AndroidEntryPoint
class MoviesFragment : BaseFragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var movieContentsAdapter: MovieContentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservables()
        setup()
    }

    private fun setupAdapter() {
        movieContentsAdapter = MovieContentsAdapter(requireContext()) {
            findNavController().navigate(
                MoviesFragmentDirections.actionFirstFragmentToSecondFragment(
                    it
                )
            )
        }
        binding.rvMovies.adapter = movieContentsAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
    }

    private fun setup() {
        viewModel.getMovies()
        binding.srlRefreshCarriers.setOnRefreshListener {
            binding.srlRefreshCarriers.isRefreshing = false
            viewModel.refreshMovies()
        }

        binding.rvMovies.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    viewModel.getMovies(false)
                }
            }
        })

    }

    private fun setupObservables() {
        setupAdapter()
        viewModel.movieState.asLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is MovieState.Loading -> showLoadingBar(true)
                is MovieState.Success -> {
                    with(state) {
                        movieContentsAdapter.setItems(content, idFavorite)
                    }
                    showLoadingBar(false)
                }

                is MovieState.Error -> {
                    showLoadingBar(false)
                    showErrorSnackBar(state.trouble)
                }

                is MovieState.Start -> Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}