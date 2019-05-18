package android.afebrerp.com.inngame.presentation.koininjection

import android.afebrerp.com.gameon.presentation.NavigatorGameOnOut
import android.afebrerp.com.gameover.presentation.navigator.NavigatorGameOverOut
import android.afebrerp.com.inngame.data.repository.HubRepositoryImpl
import android.afebrerp.com.inngame.data.repository.IndustryRepositoryImpl
import android.afebrerp.com.inngame.data.repository.TrailerRepositoryImpl
import android.afebrerp.com.inngame.domain.repository.HubRepository
import android.afebrerp.com.inngame.domain.repository.IndustryRepository
import android.afebrerp.com.inngame.domain.repository.TrailerRepository
import android.afebrerp.com.inngame.domain.usecase.CreateTrailerUseCase
import android.afebrerp.com.inngame.domain.usecase.UpdateHubUseCase
import android.afebrerp.com.inngame.domain.usecase.UpgradeIndustryUseCase
import android.afebrerp.com.navigator.gameon.NavigatorGameOnOutImpl
import android.afebrerp.com.navigator.gameover.NavigatorGameOverOutImpl
import org.koin.dsl.module.module

val navigatorModule = module {
    single<NavigatorGameOverOut> { NavigatorGameOverOutImpl() }
    single<NavigatorGameOnOut> { NavigatorGameOnOutImpl() }
}
val repositoriesModules = module {
    single<IndustryRepository> { IndustryRepositoryImpl() }
    single<HubRepository> { HubRepositoryImpl() }
    single<TrailerRepository> { TrailerRepositoryImpl() }
}

val useCasesModules = module {
    factory { UpdateHubUseCase(get()) }
    factory { UpgradeIndustryUseCase(get()) }
    factory { CreateTrailerUseCase(get()) }
}

val generalModules = listOf(
    repositoriesModules,
    useCasesModules,
    navigatorModule
)
