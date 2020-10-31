package com.circularuins.mvvmcleanarchitecture2020.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.circularuins.mvvmcleanarchitecture2020.R

class ItemListFragment : Fragment() {

    private lateinit var requestType: String

    companion object {
        private const val REQUEST_TYPE = "request_type"

        fun newInstance(requestType: String): ItemListFragment {
            val fragment = ItemListFragment()
            fragment.arguments = Bundle().apply {
                putString(REQUEST_TYPE, requestType)
            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestType = arguments?.getString(REQUEST_TYPE, "") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}