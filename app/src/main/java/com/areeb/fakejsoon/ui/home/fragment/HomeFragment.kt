package com.areeb.fakejsoon.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.areeb.fakejsoon.databinding.FragmentHomeBinding
import com.areeb.fakejsoon.ui.home.adapter.HomeAdapter
import com.areeb.fakejsoon.ui.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var homeAdapter: HomeAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        setUpViews()
    }

    private fun setUpViews() {
        settingUpRecyclerView()
    }

    private fun observer() {
        viewModel.user.observe(viewLifecycleOwner) {
            homeAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun settingUpRecyclerView() {
        homeAdapter = HomeAdapter()
        binding.recyclerView.let {
            it.adapter = homeAdapter
        }
    }
}
