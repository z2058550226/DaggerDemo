package DaggerDemo.helloworld;

import DaggerDemo.base.Command;
import DaggerDemo.output.Outputter;

import javax.inject.Inject;
import java.util.List;

final class HelloWorldCommand implements Command {
    private final Outputter outputter;

    // 使用了Inject之后就不用手动的调用new方法了，Dagger需要时就会自动构建
    // 注意：Dagger在寻找类的依赖的时候是一个递归的过程，所以不要让这个类的初始化依赖到这个类的对象
    @Inject
    HelloWorldCommand(Outputter outputter) {
        this.outputter = outputter;
        System.out.println("Creating a new " + this);
    }

    @Override
    public Result handleInput(List<String> input) {
        outputter.output("world!");
        return Result.handled();
    }
}