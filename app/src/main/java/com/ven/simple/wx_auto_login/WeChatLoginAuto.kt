package com.ven.simple.wx_auto_login

import com.ven.assists.AssistsCore
import com.ven.assists.AssistsCore.click
import com.ven.assists.AssistsCore.setNodeText
import com.ven.assists.stepper.Step
import com.ven.assists.stepper.StepCollector
import com.ven.assists.stepper.StepImpl
import com.ven.assists.window.AssistsWindowManager
import com.ven.assists.window.AssistsWindowManager.overlayToast
import kotlinx.coroutines.delay

class WeChatLoginAuto: StepImpl() {
    override fun onImpl(collector: StepCollector) {
        collector.next(stepTag = 1){
            //第1步

            "输入账号".overlayToast()
            delay(1000)
            //通过id查找并使用第1个节点元素输入账号：x63998
            AssistsCore.findById("com.tencent.mm:id/d98").firstOrNull()?.setNodeText("x69398")

            //执行第2步
            return@next Step.get(2, delay = 2000)
        }.next(stepTag = 2){
            //第2步

            "输入密码".overlayToast()
            delay(1000)

            //通过id查找并使用第2个节点元素输入密码：xxx
            //因为账号输入框节点元素id和密码输入框节点元素id相同，所以需要使用getOrNull(1)获取第2个节点元素
            AssistsCore.findById("com.tencent.mm:id/d98").getOrNull(1)?.setNodeText("li10919820")

            //执行第3步
            return@next Step.get(3, delay = 2000)
        }.next(stepTag = 3){
            //第3步

            "点击登录".overlayToast()
            delay(1000)

            //通过id查找登录按钮并点击
            AssistsCore.findById("com.tencent.mm:id/iol").firstOrNull()?.click()

            //结束执行
            return@next Step.none
        }
    }
}