package com.example.universityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Resource
import com.example.domain.models.UniversityItem
import com.example.universityapp.adapter.UniversityItemAdapter
import com.example.universityapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null
    private var searchField: EditText? = null
    private var searchButton: Button? = null
    private var listView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var adapter: UniversityItemAdapter? = null

    private val mTag = MainActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        observeData()

        searchField = findViewById(R.id.search)
        searchButton = findViewById(R.id.ok)

        searchButton?.setOnClickListener{
            get()
        }

        listView = findViewById(R.id.listView)
        progressBar = findViewById(R.id.progressBar)
        adapter = UniversityItemAdapter()
        listView?.adapter = adapter
        listView?.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

    }

    private fun observeData(){
        viewModel?.observeData()?.observe(this, {
            when(it){
                is Resource.Data<*> -> {
                    progressBar?.visibility = View.GONE
                    Log.e(mTag, it.data.toString())
                    adapter!!.setValue(it.data as ArrayList<UniversityItem>)

                }
                is Resource.Error -> {
                    Log.e(mTag, it.exception.message.toString())
                }
                is Resource.Loading -> {
                    progressBar?.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun get(){
        val str = searchField?.text.toString()
        getUniversity(str)
    }

    private fun getUniversity(country: String){
        lifecycleScope.launch {
            val i = viewModel?.getUni(country)
            i?.collect()
        }
    }


}