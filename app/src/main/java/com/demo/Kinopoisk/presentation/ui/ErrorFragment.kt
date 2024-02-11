package com.demo.Kinopoisk.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.demo.Kinopoisk.R
import com.demo.core.noStatusBar

const val ERRORFRAGMENT = "ERRORFRAGMENT"

class ErrorFragment : Fragment() {

    lateinit var repeatButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.no_internet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().noStatusBar(need = false)
        repeatButton = view.findViewById(R.id.no_internet_button)

        parentFragmentManager.beginTransaction().replace(
            R.id.main_activity_fragment_container,
            PopularFragment.newInstance(),
            POPULARFRAGMENT
        )
            .commit()

    }

    companion object {
        fun newInstance(): ErrorFragment {
            return ErrorFragment()
        }
    }
}