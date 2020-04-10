package DaggerDemo.login;

import DaggerDemo.Database;
import DaggerDemo.base.Command;
import dagger.Binds;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface LoginCommandModule {
    // IntoMap和StringKey合起来使用相当于koin中的字符串标识符named(String)，同理的还有IntoSet
    @Binds
    @IntoMap // IntoMap会创造一个可以被请求的Map<String, Command>
    @StringKey("login") // Dagger默认推断使用的是类型作为键值，当类型重复的时候可以使用这里的字符串键值做进一步判断
    Command loginCommand(LoginCommand command);

    @Binds
    @IntoMap
    @StringKey("logout")
    Command logoutCommand(LogoutCommand command);

    @BindsOptionalOf
    Database.Account optionalAccount();
}