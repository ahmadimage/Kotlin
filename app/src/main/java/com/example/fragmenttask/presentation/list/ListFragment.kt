package com.example.fragmenttask.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttask.R
import com.example.fragmenttask.base.BaseViewModel
import com.example.fragmenttask.base.MyDataBindingComponent
import com.example.fragmenttask.data.models.Devices
import com.example.fragmenttask.databinding.FragmentListBinding
import com.example.fragmenttask.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : BaseFragment() {

    val args: ListFragmentArgs by navArgs()

    private lateinit var rvList: RecyclerView
    private lateinit var retroAdapter: RetroAdapter
    var prodId: String? = null
    var notifAccess: Boolean = true

    private val deviceListViewModel: ListViewModel by viewModel()
    private var dataBindingComponent = MyDataBindingComponent(this)
    private lateinit var binding: FragmentListBinding

    override fun getViewModel(): BaseViewModel? {
        return deviceListViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var check = args.prodId
        if(check != "abcd" && notifAccess){
            //println(check)
            deviceListViewModel.getListOfDevices { data ->
                //println(data[2].brand)
                var dev = getDevice(check!!, data)
                notifAccess = false
                val action =
                    //ListFragmentDirections.actionListFragmentToDetailFragment()
                    ListFragmentDirections.actionListFragmentToDetailFragment(dev)
                findNavController().navigate(action)
                //retroAdapter.onItemClick(dev)

            }
        }
        activity?.title = "List Fragment"

        if (!this::binding.isInitialized) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list, null, false)
            retroAdapter = RetroAdapter(dataBindingComponent) {
                val direction =
                    ListFragmentDirections.actionListFragmentToDetailFragment(it)
                findNavController().navigate(direction)
            }
            binding.rvList.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            binding.rvList.adapter = retroAdapter
            addObservers()
        }
        return binding.root
    }

    fun getDevice(id: String, devList: List<Devices>): Devices {
        var tempSize = devList.size
        for (i in devList){
            if(i.productId
                == id ){
                return i
            }
        }
        return devList[1]
    }

    private fun addObservers() {
        deviceListViewModel.getListLiveData()
            .observe(viewLifecycleOwner, { resultList ->
                resultList.let {
                    retroAdapter.submitList(it)
                }
            })
        deviceListViewModel.fetchList()
    }


//    private fun getCurrentData() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://run.mocky.io/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(ApiInterface::class.java)
//        val call = service.getCurrentRetroData()
//
//        call.enqueue(object : Callback<RetroResponse> {
//            override fun onResponse(
//                call: Call<RetroResponse>?, response: Response<RetroResponse>?
//            ) {
//                if (response != null) {
//                    makeText(context, "Response Successful", Toast.LENGTH_LONG)
//                        .show()
//                    if (response.code() == 200) {
//
//                        val retroResponse = response.body()
//                        var de = retroResponse?.devices?.let { getDevice(prodId, it) }
//
//                        val layoutManager = LinearLayoutManager(context)
//                        layoutManager.orientation = LinearLayoutManager.VERTICAL
//                        rvList.layoutManager = layoutManager
//
//                        retroAdapter = RetroAdapter {
//
//                            val direction =
//                                ListFragmentDirections.actionListFragmentToDetailFragment(it)
//                            findNavController().navigate(direction)
//                        }
//
//                        if (de != null) {
//                            retroAdapter.onItemClick(de)
//
//                        } else {
//                            makeText(context, "No product Id provided", Toast.LENGTH_SHORT).show()
//                        }
//
//                        if (retroResponse != null) {
//                            retroAdapter.submit(retroResponse.devices)
//                        }
//                        rvList.adapter = retroAdapter
//                    }
//                }
//            }
//
//            override fun onFailure(
//                call: Call<RetroResponse>?, t: Throwable?
//            ) {
//                makeText(context, "Response Failure", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
//    private fun getDevice(id: String?, dataList: ArrayList<Devices>): Devices? {
//        for (i in dataList) {
//            if (i.productId
//                == id
//            ) {
//                return i
//            }
//        }
//        return null
//    }

}


