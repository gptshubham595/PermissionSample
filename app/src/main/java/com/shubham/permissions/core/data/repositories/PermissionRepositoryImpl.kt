package com.shubham.permissions.core.data.repositories

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.shubham.permissions.core.domain.repositories.PermissionRepository
import javax.inject.Inject

class PermissionRepositoryImpl @Inject constructor() :
    PermissionRepository {

    override fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestPermission(fragment: Fragment, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(
            fragment.requireActivity(),
            arrayOf(permission),
            requestCode
        )
    }
}