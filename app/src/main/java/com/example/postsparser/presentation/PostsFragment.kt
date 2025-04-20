package com.example.postsparser.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsparser.R
import com.example.postsparser.databinding.FragmentPostsBinding
import com.example.postsparser.di.App
import kotlinx.coroutines.launch
import javax.inject.Inject


class PostsFragment @Inject constructor() : Fragment(),OnPostClickListener {

    private var _binding : FragmentPostsBinding?=null

    private val binding get() = _binding ?: throw RuntimeException("PostsFragment binding except")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[PostsViewModel::class.java]
    }

    @Inject
    lateinit var postAdapter : PostAdapter

    private val component by lazy {
        (requireActivity().application as App).component.activityComponentFactory().create(6,this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = postAdapter
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.state.collect{ state ->
                    when (state) {
                        State.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.GONE
                            Log.e("PostsFragment", "Ошибка загрузки данных")
                        }
                        State.Progress -> {
                            binding.recyclerView.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is State.Result -> {
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            Log.d("PostsFragment", "Данные получены: ${state.list.size} элементов")
                            postAdapter.submitList(state.list)
                        }
                    }
                }
            }

        }


        


        Log.d("asd","asd")

    }

    companion object {
        fun newInstance() = PostsFragment()
    }

    override fun onPostClick(postId: Int) {
        Log.d("PostsFragment", "Clicked on post with ID: $postId")

        // Например, открываем новый фрагмент с деталями поста
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, PostInfoFragment.newInstance(postId))
            .addToBackStack(null)
            .commit()
    }

}