package com.mustafasehirli.cryptoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafasehirli.cryptoapp.adapter.RecyclerAdapter
import com.mustafasehirli.cryptoapp.databinding.ActivityMainBinding
import com.mustafasehirli.cryptoapp.model.CryptoModel
import com.mustafasehirli.cryptoapp.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() ,RecyclerAdapter.Listener{
    private var BASE_URL ="https://raw.githubusercontent.com/"
    private var cryptoMaodels:ArrayList<CryptoModel>? =null
    private lateinit var binding: ActivityMainBinding
    private lateinit var rcViewAdapter:RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //89b5264a1b9e643e85ca79e67fddc3ef48b236a0
        val layout=LinearLayoutManager(this)
        rc.layoutManager=layout
        loadData()
    }

    fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val servise = retrofit.create(CryptoAPI::class.java)
        val call = servise.getData()

        call.enqueue(object:Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
              if (response.isSuccessful){
                  response.body()?.let {
                      cryptoMaodels= ArrayList(it)
                      cryptoMaodels?.let {

                          rcViewAdapter=RecyclerAdapter(cryptoMaodels!!,this@MainActivity)
                          rc.adapter=rcViewAdapter
                      }
                  }
              }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Cliked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }
}