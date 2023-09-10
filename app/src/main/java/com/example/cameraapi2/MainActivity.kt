package com.example.cameraapi2

import android.content.pm.PackageManager
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.cameraapi2.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var cameraManager: CameraManager
    lateinit var cameraCaptureSession: CameraCaptureSession
    lateinit var cameraDevice: CameraDevice
    lateinit var captureRequest: CaptureRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPermissions()
    }

    private fun getPermissions() {
        var permissionList = mutableListOf<String>()

        if(checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                permissionList.add(android.Manifest.permission.CAMERA)
        }
        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                permissionList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if(permissionList.size > 0) requestPermissions(permissionList.toTypedArray(), 101)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach { permission ->
            if(permission != PackageManager.PERMISSION_GRANTED) getPermissions()
        }
    }
}

