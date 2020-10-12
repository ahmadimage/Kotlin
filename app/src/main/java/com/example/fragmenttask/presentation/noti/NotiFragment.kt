package com.example.fragmenttask.presentation.noti

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttask.R
import com.example.fragmenttask.base.MyDataBindingComponent
import com.example.fragmenttask.database.DateFormatter
import com.example.fragmenttask.database.NotifViewModel
import com.example.fragmenttask.database.Notification
import com.example.fragmenttask.databinding.FragmentNotiBinding


class NotiFragment : Fragment() {

    private lateinit var dateFormatter: DateFormatter
    lateinit var dataList: List<Notification>
    private lateinit var recyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notifViewModel: NotifViewModel
    private lateinit var binding: FragmentNotiBinding
    val dataBindingComponent = MyDataBindingComponent(this)

    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dateFormatter = DateFormatter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Notification Logs"
        notifViewModel = NotifViewModel(activity!!.application)
        dataList= notifViewModel.allNotifs

        if (!this::binding.isInitialized) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_noti, null, false)
            notificationAdapter = NotificationAdapter(dataList, dateFormatter, dataBindingComponent)
            binding.rvLogs.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            binding.rvLogs.adapter = notificationAdapter

        }
        return binding.root

        //dataList= notifViewModel.allNotifs


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noti, container, false)

    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_logs)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        notifViewModel = NotifViewModel(activity!!.application)
        dataList= notifViewModel.allNotifs

        notificationAdapter= NotificationAdapter(dataList, dateFormatter)
        recyclerView.adapter=notificationAdapter

        *//*recyclerView = view.findViewById(R.id.rv_logs)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        getAllNots { notis->
            notificationAdapter= NotificationAdapter(notis,dateFormatter)
            recyclerView.adapter = notificationAdapter
        }*//*

    }*/

    /*fun getAllNots(callback: (List<Notification>) -> Unit) {
        GlobalScope.launch {
            db = NotiDatabase.getDatabase(context!!, )
            dataList= db.notiDao().getAllNoti()
            mainThreadHandler.post { callback(dataList) }
        }
    }*/
}