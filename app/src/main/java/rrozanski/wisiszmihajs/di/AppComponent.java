package rrozanski.wisiszmihajs.di;

import android.content.Context;

import dagger.Component;
import rrozanski.wisiszmihajs.MainActivity;

/**
 * Created by Robert Różański on 27.08.2017.
 */

@Component(
        modules = {
                ContextModule.class
        }
)
public interface AppComponent {
    Context context();

    void inject(MainActivity mainActivity);
}