package com.example.pixabayapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabayapp.databinding.FragmentMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
    var adapter = ImageAdapter(listOf())
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
    }

    private fun initClickers() {
        binding.btnDownload.setOnClickListener {
            onRequest()
        }
    }

    private fun onRequest() {

        binding.rvPixaMages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.e("ololo", "" + dy)
                if (dy > 5)
                    RetrofitService().api.getImage(binding.etWord.text.toString())
                        .enqueue(
                            object : Callback<PixaModel> {
                                override fun onResponse(
                                    call: Call<PixaModel>,
                                    response: Response<PixaModel>
                                ) {
                                    if (response.isSuccessful) {
                                        adapter = ImageAdapter(response.body()?.hits!!)
                                        binding.rvPixaMages.adapter = adapter
                                    }
                                }

                                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                                    Log.e("ololo", "OnFailure: ${t.message}")
                                }

                            }
                        )
            }
        })


    }
}