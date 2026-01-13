package com.paul.dailyquoteapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.paul.dailyquoteapp.R
import com.paul.dailyquoteapp.databinding.FragmentHomeBinding
import com.paul.dailyquoteapp.model.QuoteModel
import com.paul.dailyquoteapp.viewModel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var currentQuote: QuoteModel? = null   // <-- changed (nullable)
    private  val viewModel: QuoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)






        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // disable buttons before first load
        binding.btnFavorite.isEnabled = false
        binding.btnShare.isEnabled = false

        viewModel.quote.observe(viewLifecycleOwner) { zenQuote ->
            binding.tvQuote.text = zenQuote.q
            binding.tvAuthor.text = "- ${zenQuote.a}"

            currentQuote = QuoteModel(zenQuote.q, zenQuote.a)

            // enable actions now
            binding.btnFavorite.isEnabled = true
            binding.btnShare.isEnabled = true
        }

        binding.btnNewQuote.setOnClickListener {
            viewModel.loadQuote()
        }

        // initial load
        viewModel.loadQuote()

        binding.btnFavorite.setOnClickListener { saveFavorite() }
        binding.btnShare.setOnClickListener { shareQuote() }

    }
  /*  private fun saveFavorite() {
        val quote = currentQuote ?: return   // safe guard
        val prefs = requireContext().getSharedPreferences("fav", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("list", mutableSetOf())!!.toMutableSet()
        set.add("${quote.text}||${quote.author}")
        prefs.edit().putStringSet("list", set).apply()

        Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
    }*/

    private fun saveFavorite() {
        val quote = currentQuote ?: return
        viewModel.saveFavorite(quote.text, quote.author)
        Toast.makeText(requireContext(), "Saved to Favorites", Toast.LENGTH_SHORT).show()
    }


    private fun shareQuote() {
        val quote = currentQuote ?: return   // safe guard
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "${quote.text} - ${quote.author}")
        startActivity(Intent.createChooser(intent, "Share"))
    }
}
