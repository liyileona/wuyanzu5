package AJ;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import java.lang.reflect.Field;

/**
 * Created by ger on 2017/5/27.
 */
@Aspect
public class AspectTest {
    private String TAG="TAG-";
    private String TAG_IntentTrack = "IntentTrack";
    private String TAG_GetIntent = "GetIntent";
    private String TAG_Activity ="Activity";
    private String TAG_Service = "Service";
    private String TAG_Broadcast="Broadcast";
    private String TAG_Argument ="Argument";
    private String TAG_PendingIntent = "PendingIntent";

    private String IntentType ="android.content.Intent";

    //print the arguments' name (values)
    private void LogArgsPrint(Object[] args)
    {
        if (args == null || args.length <= 0) {
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if(args[i].getClass().getName().equals(IntentType)) {
                Log.d(TAG + TAG_GetIntent, args[i].toString());
                ArgumentsPrint(args[i]);//print the values
            }
        }
        return;
    }

    //print the arguments' value
    private void ArgumentsPrint(Object arg)
    {
        for (Field field:arg.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try{
                Log.d(TAG+TAG_Argument,field.getName()+":"+field.get(arg).toString());
            }catch(Exception e){
                continue;
            }
        }
    }

    @Before("call(* *Intent*(..))")
    public void AllIntentExePoint(JoinPoint thisJoinPoint) {
        Log.d(TAG+TAG_IntentTrack,thisJoinPoint.toString());
        Log.d(TAG+TAG_IntentTrack,thisJoinPoint.getKind());
        Log.d(TAG+TAG_IntentTrack, thisJoinPoint.getSignature().toString());
        Log.d(TAG+TAG_IntentTrack, thisJoinPoint.getTarget().toString());
        Log.d(TAG+TAG_IntentTrack, thisJoinPoint.getStaticPart().toString());
        Log.d(TAG+TAG_IntentTrack,thisJoinPoint.getSourceLocation().toString());
        LogArgsPrint(thisJoinPoint.getArgs());
        return;
    }
    @Before("call(* *startActivity*(..))")
    public void ActivityIntentPoint(JoinPoint thisJoinPoint) {
        Log.d(TAG + TAG_Activity, thisJoinPoint.toString());
        Log.d(TAG + TAG_Activity,thisJoinPoint.getKind());
        Log.d(TAG + TAG_Activity, thisJoinPoint.getSignature().toString());
        Log.d(TAG + TAG_Activity, thisJoinPoint.getTarget().toString());
        Log.d(TAG + TAG_Activity, thisJoinPoint.getStaticPart().toString());
        Log.d(TAG + TAG_Activity,thisJoinPoint.getSourceLocation().toString());
        LogArgsPrint(thisJoinPoint.getArgs());
        return;
    }
    @Before("call(* *startService*(..)) && call(* *bindService*(..))")
    public void  ServiceIntentPoint(JoinPoint thisJoinPoint) {
        Log.d(TAG+TAG_Service,thisJoinPoint.toString());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getKind());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getSignature().toString());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getTarget().toString());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getStaticPart().toString());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getSourceLocation().toString());
        LogArgsPrint(thisJoinPoint.getArgs());
    }
    @Before("call(* *send*Broadcast*(..))")
    public void BroadcastIntentPoint(JoinPoint thisJoinPoint) {
        Log.d(TAG+TAG_Broadcast,thisJoinPoint.toString());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getKind());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getSignature().toString());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getTarget().toString());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getStaticPart().toString());
        Log.d(TAG +TAG_Broadcast, thisJoinPoint.getSourceLocation().toString());
        LogArgsPrint(thisJoinPoint.getArgs());
        return;
    }
    @Before("call(* *.Builder.set*Intent(..))")
    public void PendingIntentPoint(JoinPoint thisJoinPoint) {
        Log.d(TAG+TAG_PendingIntent,thisJoinPoint.toString());
        Log.d(TAG + TAG_PendingIntent, thisJoinPoint.getArgs().toString());
        Log.d(TAG + TAG_PendingIntent, thisJoinPoint.getKind());
        Log.d(TAG + TAG_PendingIntent, thisJoinPoint.getSignature().toString());
        Log.d(TAG + TAG_PendingIntent, thisJoinPoint.getTarget().toString());
        Log.d(TAG + TAG_PendingIntent, thisJoinPoint.getStaticPart().toString());
        Log.d(TAG + TAG_PendingIntent, thisJoinPoint.getSourceLocation().toString());
    }

}
