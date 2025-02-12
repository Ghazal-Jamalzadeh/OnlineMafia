package com.jmzd.ghazal.onlinemafia.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jmzd.ghazal.onlinemafia.databinding.FragmentHomeBinding
import com.jmzd.ghazal.onlinemafia.view.LoginActivity


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

       // val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
       //     textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createTableCard.setOnClickListener(){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.putExtra("mode" , "create")
            startActivity(intent)
        }

        binding.joinTableCard.setOnClickListener(){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.putExtra("mode" , "join")
            startActivity(intent)
        }

    }
}