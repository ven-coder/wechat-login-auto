package com.ven.simple.wx_auto_login

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import com.blankj.utilcode.util.ScreenUtils
import com.ven.assists.service.AssistsService
import com.ven.assists.service.AssistsServiceListener
import com.ven.assists.stepper.StepManager
import com.ven.assists.window.AssistsWindowManager
import com.ven.assists.window.AssistsWindowWrapper
import com.ven.simple.wx_auto_login.databinding.OverlayBinding

object Overlay : AssistsServiceListener {

    @SuppressLint("StaticFieldLeak")
    var viewBinding: OverlayBinding? = null
        private set
        get() {
            if (field == null) {
                field = OverlayBinding.inflate(LayoutInflater.from(AssistsService.instance)).apply {
                    btnClick.setOnClickListener {
                        StepManager.execute(WeChatLoginAuto::class.java, stepTag = 1, begin = true)
                    }
                }
            }
            return field
        }

    var onClose: ((parent: View) -> Unit)? = null

    var showed = false
        private set
        get() {
            assistWindowWrapper?.let {
                return AssistsWindowManager.isVisible(it.getView())
            } ?: return false
        }

    var assistWindowWrapper: AssistsWindowWrapper? = null
        private set
        get() {
            viewBinding?.let {
                if (field == null) {
                    field = AssistsWindowWrapper(it.root, wmLayoutParams = AssistsWindowManager.createLayoutParams().apply {
                        width = (ScreenUtils.getScreenWidth() * 0.8).toInt()
                        height = (ScreenUtils.getScreenHeight() * 0.5).toInt()
                    }, onClose = this.onClose).apply {
                        minWidth = (ScreenUtils.getScreenWidth() * 0.6).toInt()
                        minHeight = (ScreenUtils.getScreenHeight() * 0.4).toInt()
                        initialCenter = true
                        viewBinding.tvTitle.text = "微信自动登录"

                    }
                }
            }
            return field
        }

    fun show() {
        if (!AssistsService.listeners.contains(this)) {
            AssistsService.listeners.add(this)
        }
        AssistsWindowManager.add(assistWindowWrapper)
    }

    fun hide() {
        AssistsWindowManager.removeView(assistWindowWrapper?.getView())
    }

    override fun onUnbind() {
        viewBinding = null
        assistWindowWrapper = null
    }
}