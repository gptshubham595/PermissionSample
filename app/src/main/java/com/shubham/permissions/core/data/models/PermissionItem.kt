package com.shubham.permissions.core.data.models

data class PermissionItem(
    val name: String,
    val permission: String,
    var isGranted: Boolean = false
)