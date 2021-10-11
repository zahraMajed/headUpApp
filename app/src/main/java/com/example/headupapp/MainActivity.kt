package com.example.headupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnSubmit: Button
    private lateinit var edCelebName: EditText

    //in order to recycler view work, the ;ist shoulb be initialized before we initialize the RV
    var celebList = arrayListOf<List<String>>()
    var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd1)
        btnSubmit = findViewById(R.id.btnSubmit)
        edCelebName = findViewById(R.id.edCelebName)

        initializeRV()
        getAPIresult()

        btnAdd.setOnClickListener() {
            intent = Intent(this, AddCeleb::class.java)
            startActivity(intent)
        }

        btnSubmit.setOnClickListener() {
            name = edCelebName.text.toString()
            if (name.isNotEmpty()) {
                for (item in celebList) {
                    if (item[0].equals(name, false)) {
                        intent = Intent(this, UpdateDelCeleb::class.java)
                        intent.putExtra("name", item[0])
                        intent.putExtra("t1", item[1])
                        intent.putExtra("t2", item[2])
                        intent.putExtra("t3", item[3])
                        intent.putExtra("id", item[4])
                        startActivity(intent)
                    }//end if
                }//end for
            }
        }//end btnSubmit listener
    }


    fun initializeRV(){
        rv_main.adapter=RecyclerAdapter(celebList)
        rv_main.layoutManager= LinearLayoutManager(this)
    }

    fun getAPIresult(){
        val apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)

        if (apiInterface != null){

            apiInterface.getDate()?.enqueue(object : Callback<List<myData.celeb>?> {

                override fun onResponse(call: Call<List<myData.celeb>?>, response: Response<List<myData.celeb>?>) {
                    val response=response.body()
                    for (item in response!!){
                        celebList.add(listOf("${item.name}",
                            "${item.taboo1}",
                            "${item.taboo2}",
                            "${item.taboo3}",
                            "${item.pk}"))
                    }
                    rv_main.adapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<myData.celeb>?>, t: Throwable) {
                    Toast.makeText(applicationContext,"Somethimg went wrong!", Toast.LENGTH_LONG).show()
                }

            })
        }
    }//end getAPIResult()


}