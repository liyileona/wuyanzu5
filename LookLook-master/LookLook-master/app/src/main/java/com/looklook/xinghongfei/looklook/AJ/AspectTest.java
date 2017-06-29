package com.looklook.xinghongfei.looklook.AJ;

/**
 * Created by GradeTwo on 2017/6/11.
 */
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.LinkedList;
import java.util.jar.Attributes.Name;
import android.util.Log;

@Aspect
public class AspectTest {
    private static final String TAG = "GradeTwo";
    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d(TAG, "onActivityMethodBefore: " + key);
    }
}