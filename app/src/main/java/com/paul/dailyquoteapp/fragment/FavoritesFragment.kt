package com.paul.dailyquoteapp.fragment

import QuoteAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.paul.dailyquoteapp.databinding.FragmentFavoritesBinding
import com.paul.dailyquoteapp.model.QuoteModel
import com.paul.dailyquoteapp.viewModel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: QuoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[QuoteViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.favoritesLiveData.observe(viewLifecycleOwner) { list ->

            binding.recyclerView.adapter = QuoteAdapter(
                list.map { q -> QuoteModel(q.quote, q.author) }
            ) { quote ->

                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "${quote.text} - ${quote.author}")
                startActivity(Intent.createChooser(intent, "Share Quote"))
            }
        }

        viewModel.loadFavorites()

        return binding.root
    }
}
