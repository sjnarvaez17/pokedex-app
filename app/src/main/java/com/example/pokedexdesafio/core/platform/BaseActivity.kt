package com.example.pokedexdesafio.core.platform

import android.app.AlertDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedexdesafio.AndroidApplication
import com.example.pokedexdesafio.R
import com.example.pokedexdesafio.core.di.ApplicationComponent
import com.example.pokedexdesafio.core.functional.Failure

abstract class BaseActivity : AppCompatActivity() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    private var indeterminateDialog: AlertDialog? = null

    private fun initIndeterminateModalDialog() {
        val activity = this
        val view = activity.layoutInflater.inflate(R.layout.modal_progress_indeterminate, null)
        indeterminateDialog = AlertDialog.Builder(activity).create()
        indeterminateDialog?.let {
            it.setCancelable(false)
            it.setView(view)
        }
    }

    fun showIndeterminateModalDialog(): Unit = indeterminateDialog?.show() ?: run {
        initIndeterminateModalDialog()
        showIndeterminateModalDialog()
    }

    fun hideIndeterminateModalDialog() =
        indeterminateDialog?.cancel().also { indeterminateDialog = null }
            ?: Unit

    fun onFailure(failure: Boolean) {
        hideIndeterminateModalDialog()
        if (failure) {
            Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show()
        }
    }

    fun onFailure(failure: Failure) {
        hideIndeterminateModalDialog()
        when(failure){
            is Failure.GenericFailure -> Toast.makeText(this, getString(R.string.error_generic), Toast.LENGTH_LONG).show()
            is Failure.ServerError -> Toast.makeText(this, getString(R.string.error_server_internal), Toast.LENGTH_LONG).show()
            is Failure.ServerNotFound -> Toast.makeText(this, getString(R.string.error_server_not_found), Toast.LENGTH_LONG).show()
            is Failure.NetworkError -> Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show()
            is Failure.LocalDatabaseError -> Toast.makeText(this, getString(R.string.error_local_database), Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        super.onStop()
        hideIndeterminateModalDialog()
    }
}