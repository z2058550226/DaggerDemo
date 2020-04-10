package DaggerDemo.user;

import javax.inject.Scope;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Scope定义一个类的对象在一个组件的声明周期内共享
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface Persession {
}
