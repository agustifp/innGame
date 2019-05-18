package android.afebrerp.com.navigator.gameover

import android.afebrerp.com.gameover.presentation.navigator.NavigatorGameOverOut
import android.content.Context
import android.content.Intent

class NavigatorGameOverOutImpl : NavigatorGameOverOut {
    override fun goToGameOnActivity(context: Context) {
        context.startActivity(Intent(context,Class.forName("android.afebrerp.com.gameon.presentation.GameOnActivity")))

    }
}