package android.afebrerp.com.navigator.gameon

import android.afebrerp.com.gameon.presentation.NavigatorGameOnOut
import android.content.Context
import android.content.Intent

class NavigatorGameOnOutImpl: NavigatorGameOnOut {
    override fun goToGameOverActivity(context: Context) {
        context.startActivity(Intent(context,Class.forName("android.afebrerp.com.gameover.presentation.GameOverActivity")))
    }
}