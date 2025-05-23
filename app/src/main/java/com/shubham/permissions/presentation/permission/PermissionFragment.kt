package com.shubham.permissions.presentation.permission

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.permissions.databinding.FragmentPermissionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermissionFragment : Fragment() {

    private lateinit var binding: FragmentPermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val viewModel: PermissionViewModel by viewModels()
    private lateinit var adapter: PermissionAdapter
    private lateinit var launcher: ActivityResultLauncher<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPermissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PermissionAdapter(viewModel.permissions.value ?: emptyList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.permissions.observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        viewModel.loadPermissions()

        launcher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

        binding.requestPermissionsBtn.setOnClickListener {
            val perms = viewModel.permissions.value ?: return@setOnClickListener
            for (item in perms) {
                launcher.launch(item.permission)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.permissions.value?.forEachIndexed { index, item ->
            val isGranted = ContextCompat.checkSelfPermission(
                requireContext(),
                item.permission
            ) == PackageManager.PERMISSION_GRANTED

            viewModel.updatePermissionStatus(index, isGranted)
        }
    }

}