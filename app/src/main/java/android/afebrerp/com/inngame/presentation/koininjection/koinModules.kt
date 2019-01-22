package android.afebrerp.com.inngame.presentation.koininjection

import android.afebrerp.com.inngame.data.repository.HubRepositoryImpl
import android.afebrerp.com.inngame.data.repository.IndustryRepositoryImpl
import android.afebrerp.com.inngame.data.repository.TrailerRepositoryImpl
import android.afebrerp.com.inngame.domain.repository.HubRepository
import android.afebrerp.com.inngame.domain.repository.IndustryRepository
import android.afebrerp.com.inngame.domain.repository.TrailerRepository
import android.afebrerp.com.inngame.domain.usecase.CreateTrailerUseCase
import android.afebrerp.com.inngame.domain.usecase.UpdateHubUseCase
import android.afebrerp.com.inngame.domain.usecase.UpgradeIndustryUseCase
import org.koin.dsl.module.module

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
    useCasesModules
)
