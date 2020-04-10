package DaggerDemo.output;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SystemOutModule {
  // Provides类似与koin的single
  @Provides
  static Outputter textOutputter() {
    return System.out::println;
  }
}