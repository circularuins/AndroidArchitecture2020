package com.circularuins.mvvmcleanarchitecture2020.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.circularuins.mvvmcleanarchitecture2020.R
import com.circularuins.mvvmcleanarchitecture2020.databinding.ActivityMainBinding
import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        toolbar.apply {
            setLogo(R.mipmap.ic_launcher)
            title = "Architecture"
        }
        setSupportActionBar(toolbar)
        ViewCompat.setElevation(toolbar, 10f)

        viewModel.masterData.observe(this, { master ->
            view_pager.adapter = FragmentPagerAdapter(this, master)
            TabLayoutMediator(tab_layout, view_pager) { tab, position ->
                tab.text = master[position].name
            }.attach()
        })


    }

    override fun onResume() {
        super.onResume()
        viewModel.initTab()
    }

    private inner class FragmentPagerAdapter(fa: FragmentActivity, val master: List<Master>) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = master.size

        override fun createFragment(position: Int): Fragment = ItemListFragment()
    }
}