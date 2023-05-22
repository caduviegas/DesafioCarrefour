package com.innaval.desafiocarrefour.presentation.user.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.innaval.desafiocarrefour.core.Status
import com.innaval.desafiocarrefour.core.extensions.navigateWithAnimations
import com.innaval.desafiocarrefour.databinding.FragmentUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModel()

    private var adapter = UserAdapter()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        initObservers()
        initListeners()
    }

    private fun setRecyclerView() {
        binding.recyclerViewUsers.also {
            it.setHasFixedSize(true)
            it.adapter = adapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initObservers() {
        viewModel.loadingStatus.observe(viewLifecycleOwner) { status ->
            binding.apply {
                when (status) {
                    Status.LOADING -> {
                        swipeRefreshSearch.isRefreshing = true
                    }

                    Status.ERROR -> {
                        swipeRefreshSearch.isRefreshing = false
                        viewFlipper.displayedChild = 1
                    }

                    else -> {
                        swipeRefreshSearch.isRefreshing = false
                    }
                }
            }
        }

        viewModel.allUsers.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
            binding.viewFlipper.displayedChild = if (users.isNullOrEmpty()) 1 else 0
        }

        viewModel.userModel.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                adapter.submitList(listOf(user))
                binding.viewFlipper.displayedChild = if (user == null) 1 else 0
            }
        }
    }

    private fun initListeners() {
        adapter.onClickListener = { userModel ->
            val directions = UserFragmentDirections
                .actionUserFragmentToUserDetailFragment(
                    userModel = userModel,
                )
            navController.navigateWithAnimations(directions)
        }

        binding.swipeRefreshSearch.setOnRefreshListener {
            binding.inputSearch.text = null
            adapter.submitList(emptyList())
            viewModel.getAllUsers()
        }

        binding.inputLayoutSearch.setEndIconOnClickListener {
            adapter.submitList(emptyList())
            val name = binding.inputSearch.text.toString().trim()
            if (name.isNullOrEmpty()) {
                viewModel.getAllUsers()
            } else {
                viewModel.getUser(name)
            }
        }
    }
}
