package com.shubham.permissions.presentation.permission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shubham.permissions.core.data.models.PermissionItem
import com.shubham.permissions.core.domain.usecases.GetPermissionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor(private val getPermissionsUseCase: GetPermissionsUseCase) :
    ViewModel() {
    private val _permissions = MutableLiveData<List<PermissionItem>>()
    val permissions: LiveData<List<PermissionItem>> get() = _permissions

    fun loadPermissions() {
        _permissions.postValue(getPermissionsUseCase.invoke())
    }

    fun updatePermissionStatus(index: Int, isGranted: Boolean) {
        val list = _permissions.value?.toMutableList()
        list?.get(index)?.isGranted = isGranted
        _permissions.postValue(list)
    }
}