package DaggerDemo;

//import DaggerDemo.helloworld.HelloWorldModule;
//import DaggerDemo.login.LoginCommandModule;
//import DaggerDemo.output.SystemOutModule;
//import DaggerDemo.user.UserCommandsRouter;
//import dagger.Component;
//
//import javax.inject.Singleton;

// Component有点相当于koin的koinApplication
// 使用Component注解之后就不必再手动实现这个类的子类了
// Component通常是一个产生对象的类（工厂类）
// Dagger在用代码生成这个类的实现时，会去找@Inject注解标记的相应类的构造方法，并使用它new一个新的对象
// 在@Component注解类上添加@Singleton表示这个组件内部的@Singleton类型会在这个组件内部共享
//@Singleton
//@Component(modules = {
//        LoginCommandModule.class,
//        HelloWorldModule.class,
//        UserCommandsModule.class,
//        UserCommandsRouter.InstallationModule.class,
//        SystemOutModule.class
//})
//interface CommandRouterFactory {
//    CommandRouter router();
//}
