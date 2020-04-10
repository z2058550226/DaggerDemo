package DaggerDemo.user;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 这里的@Qualifier注解也是用来区分相同类型的不同定义（@Providers，@Binds）的。
 * 与@IntoSet和@IntoMap不同，它不会将这个定义收集到一个集合中去请求，而是请求的时候需要对参数进行相应的注解。
 *
 * Qualifiers通常用于这样的需求：将一些数字，字符串之类的基本类型放在一个全局可以访问的位置
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface MaximumWithdrawal {
}

