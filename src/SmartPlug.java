import java.util.Calendar;

public class SmartPlug extends SmartObject implements Programmable{

    private boolean status;
    private Calendar programTime;
    private boolean programAction;

    public SmartPlug(String alias, String macId){
        super.setAlias(alias);
        super.setMacId(macId);
    }

    public void turnOn(){

        if(this.controlConnection()){
            if(!status) {
                Calendar calendar = Calendar.getInstance();

                System.out.println("Smart Plug - " + getAlias() + " is turned on now (Current time: " + getCurrentTime() +" )");
                setStatus(true);
                setProgramTime(calendar);
                setProgramAction(false);
            }
            else{
                System.out.println("Smart Plug - " + getAlias() + "has been already turned on");
            }
        }
    }

    public void turnOff(){
        if(this.controlConnection()) {
            if (status = true) {
                Calendar calendar = Calendar.getInstance();

                System.out.println("Smart Plug - " + getAlias() + " is turned off now (Current time: " + getCurrentTime() +" )");
                setStatus(false);
                setProgramTime(calendar);
                setProgramAction(true);
            }
            else {
                System.out.println("Smart Plug - " + getAlias() + "has been already turned off");
            }
        }
    }



    @Override
    public boolean testObject() {
        if(this.controlConnection()) {
            System.out.println("Test is starting for " + this.getClass().getName());
            SmartObjectToString();
            turnOn();
            turnOff();
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
            if (status) {
                SmartObjectToString();
                turnOff();
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void setTimer(int seconds) {
        if(this.controlConnection()) {
            Calendar currentTime = Calendar.getInstance();
            setProgramTime(currentTime);
            programTime.add(Calendar.SECOND, seconds);

            if(status){
                System.out.println(this.getClass().getName() + " - " +getAlias() +" will be turned off "+ seconds + " seconds later");
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
                    turnOn();

                }
                else{
                    System.out.println("Run program -> " + this.getClass().getName() + " - " + getAlias());
                    System.out.println(this.getClass().getName() + " - " + getAlias() + " is turned off now (Current time: " + getCurrentTime() + ")");
                    turnOff();

                }
                setProgramTime(null);
            }
        }

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
