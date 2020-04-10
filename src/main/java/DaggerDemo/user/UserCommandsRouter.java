package DaggerDemo.user;

import DaggerDemo.CommandRouter;
import DaggerDemo.Database;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;

/**
 * We can introduce a @Subcomponent to help. A @Subcomponent is similar to a @Component:
 * it has abstract methods that Dagger implements, and it can use @Modules. It always has a parent component,
 * and it can access any type that the parent component can access.
 * Any types it creates are hidden from the parent component.
 * <p>
 * Let’s create a @Subcomponent that adds Commands for a logged-in user.
 * It will share the same Database that exists in the CommandProcessorFactory component,
 * which will be useful when we want to deposit and withdraw money from a particular user.
 *
 * Subcomponent和Component相当于子系统和系统，Subcomponent会重新在自己的组件内部生成一套隔离出来的Component环境，除了Singleton
 * 这种组件内共享的对象
 */
@Persession
@Subcomponent(modules = UserCommandsModule.class)
public interface UserCommandsRouter {
    CommandRouter router();

    // 定义这个子组件在构造时需要依赖的对象, 并且在需要使用这个子组件时可以在Dagger中请求这个Factory类
    @Subcomponent.Factory
    interface Factory {
        UserCommandsRouter create(@BindsInstance Database.Account account);
    }

    // 这个写在外面也行，只是定义了一个@Subcomponent和component的module，以便在@Component声明这个子组件
    @Module(subcomponents = UserCommandsRouter.class)
    interface InstallationModule {
    }
}
