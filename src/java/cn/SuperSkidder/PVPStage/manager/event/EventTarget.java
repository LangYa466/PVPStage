package cn.SuperSkidder.PVPStage.manager.event;


import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventTarget {
    byte value() default Priority.MEDIUM;
}
