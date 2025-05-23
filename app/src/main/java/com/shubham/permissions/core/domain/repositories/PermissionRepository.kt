package com.shubham.permissions.core.domain.repositories

import android.content.Context
import androidx.fragment.app.Fragment

interface PermissionRepository {
    fun checkPermission(context: Context, permission: String): Boolean
    fun requestPermission(fragment: Fragment, permission: String, requestCode: Int)
}