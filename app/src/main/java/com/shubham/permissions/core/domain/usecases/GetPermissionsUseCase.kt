package com.shubham.permissions.core.domain.usecases

import android.Manifest
import com.shubham.permissions.core.data.models.PermissionItem
import javax.inject.Inject

class GetPermissionsUseCase @Inject constructor(){
    operator fun invoke(): List<PermissionItem> = listOf(
        PermissionItem("Location", Manifest.permission.ACCESS_FINE_LOCATION),
        PermissionItem("Camera", Manifest.permission.CAMERA),
        PermissionItem("Photos", Manifest.permission.READ_MEDIA_IMAGES)
    )
}