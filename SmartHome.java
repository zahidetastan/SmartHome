import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SmartHome {
    private ArrayList<SmartObject> smartObjectList  = new ArrayList<SmartObject>();

    public SmartHome(){}

    public boolean addSmartObject(SmartObject smartObject){
        if(smartObjectList.isEmpty()){
            System.out.println("-------------------------------------------------");
            System.out.println("-------------------------------------------------");
            System.out.println("Adding new Smart Object");
            System.out.println("-------------------------------------------------");
            smartObject.connect("10.0.0.100");
            smartObjectList.add(smartObject);
            smartObject.testObject();
            return true;
        }
        else{
            String IpVal = (smartObjectList.get(smartObjectList.size() - 1 ).getIP()).replace(".","");
            Double IpValueAdd = Double.parseDouble(IpVal);
            IpValueAdd = IpValueAdd + 1;
            String Ip = String.valueOf(IpValueAdd);
            String IP =  Ip.substring(0,2) + "." + Ip.substring(2,3) + "." + Ip.substring(3,4) + "." + Ip.substring(4,7);
            System.out.println("-------------------------------------------------");
            System.out.println("-------------------------------------------------");
            System.out.println("Adding new Smart Object");
            System.out.println("-------------------------------------------------");
            smartObject.connect(IP);
            smartObjectList.add(smartObject);
            smartObject.testObject();
            return true;
        }
    }
    public boolean removeSmartObject(SmartObject smartObject){
            smartObjectList.remove(smartObject);
            return true;
    }

    /*test for removeSmartObject method
    public void listObject(){
        for (int i = 0; i<smartObjectList.size(); i++){
            System.out.println(smartObjectList.get(i).getAlias());
        }
    } */

    public void controlLocation(boolean onCome){
        for(int i = 0; i<smartObjectList.size(); i++){
            if(smartObjectList.get(i) instanceof LocationControl){
                if(onCome) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Location Control : OnCome");
                    System.out.println("-------------------------------------------------");
                    ((LocationControl) smartObjectList.get(i)).onCome();
                }
                else{
                    System.out.println("-------------------------------------------------");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Location Control : OnLeave");
                    System.out.println("-------------------------------------------------");
                    ((LocationControl) smartObjectList.get(i)).onLeave();
                }
            }
        }
    }

    public void controlMotion(boolean hasMotion, boolean isDay){
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("Motion Control : HasMotion, isDay ");
        System.out.println("-------------------------------------------------");
        for(int i = 0; i<smartObjectList.size(); i++){
            if(smartObjectList.get(i) instanceof MotionControl) {
                ((MotionControl) smartObjectList.get(i)).controlMotion(hasMotion, isDay);
            }
        }
    }

    public void controlProgrammable(){
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("Programmable: runProgram");
        System.out.println("-------------------------------------------------");
        for(int i = 0; i<smartObjectList.size(); i++){
            if(smartObjectList.get(i) instanceof Programmable) {
                ((Programmable) smartObjectList.get(i)).runProgram();
            }
        }
    }

    public void controlTimer(int seconds){
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("Programmable: Timer = " +seconds +" seconds");
        System.out.println("-------------------------------------------------");
        for(int i = 0; i<smartObjectList.size(); i++){
            if(smartObjectList.get(i) instanceof Programmable) {
                if(seconds != 0) {
                    ((Programmable) smartObjectList.get(i)).setTimer(seconds);
                }
                else{
                    ((Programmable) smartObjectList.get(i)).cancelTimer();
                }
            }
        }
    }

    public void controlTimerRandomly(){
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("Programmable: Timer = 0, 5 or 10 seconds randomly");
        System.out.println("-------------------------------------------------");
        for(int i = 0; i<smartObjectList.size(); i++){
            int[] intArray = new int[]{ 0,5,10 };
            int rnd = new Random().nextInt(intArray.length);
            if(smartObjectList.get(i) instanceof Programmable) {
                if(rnd == 0){
                    ((Programmable) smartObjectList.get(i)).cancelTimer();
                }
                else if(rnd == 1){
                    ((Programmable) smartObjectList.get(i)).setTimer(5);
                }
                else if(rnd == 2){
                    ((Programmable) smartObjectList.get(i)).setTimer(10);
                }
            }
        }
    }

    public void sortCameras(){
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("Sort Smart Cameras");
        System.out.println("-------------------------------------------------");
        int numberOfComp = 0;

        for(int j = 0; j<smartObjectList.size(); j++) {
            if(smartObjectList.get(j) instanceof Comparable) {
                numberOfComp++;
            }
        }

        SmartCamera obj[]= new SmartCamera[numberOfComp];
        int k=0;
        for(int i = 0; i<smartObjectList.size(); i++) {
            if(smartObjectList.get(i) instanceof Comparable) {
                obj[k++] = (SmartCamera) smartObjectList.get(i);
                // comparableArray.add((SmartCamera)smartObjectList.get(i));
            }
        }
        Arrays.sort(obj);
        for(int i=0;i<obj.length;i++) System.out.println(obj[i].toString());
    }
}