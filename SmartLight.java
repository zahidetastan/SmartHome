import java.util.Calendar;

public class SmartLight extends SmartObject implements LocationControl, Programmable{

    private boolean hasLightTurned = false;
    private Calendar programTime;
    private boolean programAction;

    public SmartLight(String alias, String macId) {
        super.setAlias(alias);
        super.setMacId(macId);
    }

    public void turnOnLight(){

        if(this.controlConnection()){
            if(!hasLightTurned) {
                Calendar calendar = Calendar.getInstance();
                System.out.println("Smart Light - " + getAlias() + " is turned on now (Current time: " + getCurrentTime() +")");
                setHasLightTurned(true);
                setProgramTime(calendar);
                setProgramAction(false);
        }
        else{
            System.out.println("Smart Light - " + getAlias() + "has been already turned on");
        }
      }
    }

    public void turnOffLight(){
        if(this.controlConnection()) {
            if (hasLightTurned = true) {
                Calendar calendar = Calendar.getInstance();

                System.out.println("Smart Light - " + getAlias() + " is turned off now (Current time: " + getCurrentTime() +")");
                setHasLightTurned(false);
                setProgramTime(calendar);
                setProgramAction(true);
            }
            else {
                System.out.println("Smart Light - " + getAlias() + "has been already turned off");
            }
        }
    }

    @Override
    public boolean testObject(){
        if(this.controlConnection()) {
            System.out.println("Test is starting for " + this.getClass().getName());
            SmartObjectToString();
            turnOnLight();
            turnOffLight();
            System.out.println("Test completed for " + this.getClass().getName());
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean shutDownObject() {
        if (this.controlConnection()) {
            if (hasLightTurned) {
                SmartObjectToString();
                turnOffLight();
            }
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public void onLeave() {
        if(this.controlConnection()) {
            System.out.println("On Leave -> Smart Light - " +getAlias());
            System.out.println("Smart Light - " + getAlias() + "is turned off now (Current time: " +getCurrentTime() + ")");
            turnOffLight();
        }
    }

    @Override
    public void onCome() {
        if(this.controlConnection()) {
            System.out.println("On Come -> Smart Light - " +getAlias());
            System.out.println("Smart Light - " + getAlias() + "is turned on now (Current time: " +getCurrentTime() + ")");
            turnOnLight();
        }
    }

    @Override
    public void setTimer(int seconds) {
        if(this.controlConnection()) {
            Calendar currentTime = Calendar.getInstance();
            setProgramTime(currentTime);
            programTime.add(Calendar.SECOND, seconds);

            if(hasLightTurned){
                System.out.println(this.getClass().getName() + " - " +getAlias() +" will be turned off " + seconds + " seconds later");
                System.out.println("(Current time: " + getCurrentTime() + ")");
            }
            else{
                System.out.println(this.getClass().getName() + " - " +getAlias() +" will be turned on " + seconds + " seconds later");
                System.out.println("(Current time: " + getCurrentTime() + ")");
            }
        }
    }

    @Override
    public void cancelTimer() {
        if(this.controlConnection()) {
            setProgramTime(null);
        }
    }

    @Override
    public void runProgram() {
        Calendar calendar = Calendar.getInstance();
        if(this.controlConnection()) {
           if(programTime != null && calendar.get(Calendar.MINUTE) == programTime.get(Calendar.MINUTE) && calendar.get(Calendar.SECOND) == programTime.get(Calendar.SECOND)){
               if(programAction){
                   System.out.println("Run program -> " + this.getClass().getName() + " - " + getAlias());
                   System.out.println(this.getClass().getName() + " - " + getAlias() + " is turned on now (Current time: " + getCurrentTime() + ")");
                   setProgramTime(null);
                   turnOnLight();
               }
               else{
                   System.out.println("Run program -> " + this.getClass().getName() + " - " + getAlias());
                   System.out.println(this.getClass().getName() + " - " + getAlias() + " is turned off now (Current time: " + getCurrentTime() + ")");
                   setProgramTime(null);
                   turnOffLight();

               }
           }
        }
    }

    public boolean isHasLightTurned() {
        return hasLightTurned;
    }

    public void setHasLightTurned(boolean hasLightTurned) {
        this.hasLightTurned = hasLightTurned;
    }

    public Calendar getProgramTime() {
        return programTime;
    }

    public void setProgramTime(Calendar programTime) {
        this.programTime = programTime;
    }

    public boolean isProgramAction() {
        return programAction;
    }

    public void setProgramAction(boolean programAction) {
        this.programAction = programAction;
    }


}
