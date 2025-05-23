package com.shubham.permissions.core.domain.usecases

import android.content.Context
import com.shubham.permissions.core.data.models.PermissionItem
import com.shubham.permissions.core.domain.repositories.PermissionRepository
import javax.inject.Inject

class CheckPermissionUseCase @Inject constructor(
    private val repo: PermissionRepository
) {
    operator fun invoke(context: Context, item: PermissionItem): Boolean {
        return repo.checkPermission(context, item.permission)
    }
}