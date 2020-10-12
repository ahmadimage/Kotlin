package com.example.fragmenttask.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.fragmenttask.R
import com.example.fragmenttask.database.NotiDatabase
import com.example.fragmenttask.database.NotifViewModel
import com.example.fragmenttask.database.Notification
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

lateinit var db: NotiDatabase

class MainActivity : AppCompatActivity() {

    var text: String? = null
    private lateinit var notifViewModel: NotifViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token
                Log.d("MyTag", "Generated token: $token")
            })

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bundle = intent.extras
        if (bundle != null) {
            text = bundle.get("prodId") as String?

            val noti = text?.let { Notification(it, System.currentTimeMillis()) }
            db = NotiDatabase.getDatabase(this)

            Log.d("Inserted", "onCreate: ")
            notifViewModel = NotifViewModel(application, )

            GlobalScope.launch {
                if (noti != null) notifViewModel.insert(noti)
                val devBundle = Bundle()
                devBundle.putString("prodId", text)
                navController.setGraph(navController.graph, devBundle)
            }
        }
    }
}