package com.example.farmmanagmentapp

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.farmmanagmentapp.databinding.FragmentHomeBinding

class homeFragment : Fragment() {

    var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewDataBtn.setOnClickListener { view: View ->
                findNavController(view).navigate(R.id.action_homeFragment_to_veiwDataHome)
            }

            //Navigation to feedback
            feedbackBtn.setOnClickListener { view: View ->
                findNavController(view).navigate(R.id.action_homeFragment_to_feedbackHome)
            }

            //Navigate to Mass actions
            massActionsBtn.setOnClickListener { view: View ->
                findNavController(view).navigate(R.id.action_homeFragment_to_massActionsHome)
            }

            //Navigate to mating
            matingBtn.setOnClickListener {
                findNavController(it).navigate(R.id.action_homeFragment_to_matingHome)
            }

            //Navigate to birth
            recordBirthBtn.setOnClickListener {
                findNavController(it).navigate(R.id.action_homeFragment_to_birthMothersInfo)
            }
            medicineBtn.setOnClickListener {
                findNavController(it).navigate(R.id.action_homeFragment_to_medicineSelectAnimals)
            }
        }
    }
}