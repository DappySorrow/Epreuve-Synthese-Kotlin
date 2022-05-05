package com.tp_apps.presentation.ui.capture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tp_apps.R
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig

class CaptureActivity : AppCompatActivity() {

    private lateinit var binding: CaptureActivity
    private val viewModel: CaptureViewModel by viewModels()

    private val quickieActivityLauncher =
        registerForActivityResult(ScanQRCode(), ::handleQuickieQRResult)

    private val codeBarActivityLauncher = registerForActivityResult(ScanCustomCode(), ::handleQuickieCodeBarre)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        codeBarActivityLauncher.launch(
            ScannerConfig.build {
                setBarcodeFormats(listOf(BarcodeFormat.FORMAT_ALL_FORMATS)) // set interested barcode formats
                setOverlayStringRes(R.string.quickie) // string resource used for the scanner overlay
                //setOverlayDrawableRes(R.drawable.ic_scan_barcode) // drawable resource used for the scanner overlay
                setHapticSuccessFeedback(false) // enable (default) or disable haptic feedback when a barcode was detected
                setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
                setHorizontalFrameRatio(1f) // set the horizontal overlay ratio (default is 1 / square frame)
                setUseFrontCamera(false) // use the front camera
            }
        )
    }


    private fun handleQuickieQRResult(qrResult: QRResult) {
        when (qrResult) {
            is QRResult.QRSuccess -> {
                //TODO
            }
            is QRResult.QRUserCanceled -> {
                //TODO
            }
            is QRResult.QRMissingPermission -> {
                //TODO
            }
            is QRResult.QRError -> {
                //TODO
            }
        }
    }

    private fun handleQuickieCodeBarre(qrResult: QRResult) {
        when (qrResult) {
            is QRResult.QRSuccess -> {
                //TODO
            }
            is QRResult.QRUserCanceled -> {
                //TODO
            }
            is QRResult.QRMissingPermission -> {
                //TODO
            }
            is QRResult.QRError -> {
                //TODO
            }
        }
    }

    //Pour faire un intent d'une CaptureActivity
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CaptureActivity::class.java)
        }
    }
}