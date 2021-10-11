package com.example.myapp.Fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.Activities.WebViewActivity
import com.example.myapp.Adapters.NewsRecyclerAdapter
import com.example.myapp.databinding.FragmentNewsBinding
import com.example.myapp.viewModels.NewsFragmentViewModel

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var recyclerAdapter: NewsRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterSet()
        initViewModel()
    }

    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(NewsFragmentViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, {
            if(it != null){
                recyclerAdapter.setUpdatedData(it.articles)
            } else {
                Log.d("mm", "Error in loading data")
            }
        })
        viewModel.makeApiCall()
    }

    fun adapterSet() {
        binding.recyclerNews.layoutManager = LinearLayoutManager(requireActivity())
        recyclerAdapter = NewsRecyclerAdapter()
        binding.recyclerNews.adapter = recyclerAdapter
        recyclerAdapter.setOnItemClickListener(object : NewsRecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int, url: String) {
                val intent = Intent(requireContext(), WebViewActivity::class.java)
                intent.putExtra("newsUrl", url)
                startActivity(intent)
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }
}