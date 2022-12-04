package com.ispsolutionsofficial.coroutinesdemo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var progressBarDialog:Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnExecute:Button = findViewById(R.id.btnExecute)

        btnExecute.setOnClickListener {
            showProgressBarDialog()
            lifecycleScope.launch {
                execute("Task executed Successfully!!")
            }
        }
    }

    private suspend fun execute(message:String) {
        withContext(Dispatchers.IO) {
            for(i in 1..10000000) {
                Log.e("delay", "" + i)
            }
            runOnUiThread {
                progressBarDialog?.dismiss()
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun showProgressBarDialog() {
        progressBarDialog = Dialog(this)
        progressBarDialog?.setContentView(R.layout.progress_bar_dialog)
        progressBarDialog?.setCancelable(false)
        progressBarDialog?.show()
    }
}