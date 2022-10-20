package com.example.maria_study_app

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


object PermissionUtil {

    private  var resultLauncher: ActivityResultLauncher<Intent>? = null
    private  var oldResultLauncher: ActivityResultLauncher<String>? = null

    fun hasPermissions(context: Context): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                Environment.isExternalStorageManager()
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED)
            }
            else -> {
                true
            }
        }
    }

    fun registerLauncher(launcher: ActivityResultLauncher<Intent>) {
        resultLauncher = launcher
    }

    fun oldRegisterLauncher(launcher: ActivityResultLauncher<String>) {
        oldResultLauncher = launcher
    }

    fun checkVersion(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    fun requestPermissions(
        activity: Activity
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse(String.format("package:%s", activity.packageName))
                resultLauncher?.launch(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            oldResultLauncher?.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }
}
