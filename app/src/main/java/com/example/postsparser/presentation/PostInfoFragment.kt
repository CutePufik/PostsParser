package com.example.postsparser.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.postsparser.R
import com.example.postsparser.databinding.FragmentPostBinding
import com.example.postsparser.di.App
import kotlinx.coroutines.launch
import javax.inject.Inject


class PostInfoFragment : Fragment() {

    private var _binding : FragmentPostBinding? =null

    private val binding get() = _binding ?: throw Exception("bad")


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[PostInfoViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as App).component.postInfoFactory().create(requireArguments().getInt("idPost"))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = FragmentPostBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch{


            viewModel.state.collect{ state ->
                when(state){
                    PostState.Error -> {

                    }
                    PostState.Loading -> {

                    }
                    is PostState.Result -> {
                        val post = state.post
                        binding.tvUserName.text = post.title
                        binding.tvPost.text = post.body
                    }
                }
            }

        }


    }

    companion object {
        fun newInstance(idPost: Int) = PostInfoFragment().apply {
            arguments = Bundle().apply {
                putInt("idPost", idPost)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}