package com.denysnovoa.nzbmanager.di.modules

import android.app.Fragment
import android.content.Context
import com.denysnovoa.nzbmanager.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
abstract class FragmentModule(protected val fragment: Fragment) {

    @Provides @FragmentScope
    fun provideContext(): Context = fragment.activity.baseContext
}
