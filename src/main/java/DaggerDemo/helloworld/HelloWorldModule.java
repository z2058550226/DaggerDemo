package DaggerDemo.helloworld;

import DaggerDemo.base.Command;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

// Module注解表示一系列的绑定方法的集合
@Module
public abstract class HelloWorldModule {
    // Binds注解只能在Module中使用，和koin中的bind类似，这个注解向Dagger指明在请求一个对象的时候调用哪个对象的inject注解标记的方法
    @Binds
    @IntoMap
    @StringKey("hello")
    abstract Command helloWorldCommand(HelloWorldCommand helloWorldCommand);
}
