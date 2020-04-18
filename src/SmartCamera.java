public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera>{

    private boolean status;
    private int batteryLife;
    private boolean nightVision;

    public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife){
        super.setAlias(alias);
        super.setMacId(macId);
        this.nightVision = nightVision;
        this.batteryLife = batteryLife;
    }

    public void recordOn(boolean isDay){
        if(this.controlConnection()) {
            if(isDay){
                if(status){
                    System.out.println(this.getClass().getName() + " - " + getAlias() + " has been already turned on");
                }
                else{
                    System.out.println(this.getClass().getName() + " - " + getAlias() + " is turned on now");
                    setStatus(true);
                }
            }
            else {
                if (status) {
                    System.out.println(this.getClass().getName() + " - " + getAlias() + " has been already turned on");
                } else {
                    if (nightVision) {
                        System.out.println(this.getClass().getName() + " - " + getAlias() + " is turned on now");
                        setStatus(true);
                    } else {
                        System.out.println("Sorry! " + this.getClass().getName() + " - " + getAlias() + " does not have night vision feature.");
                    }
                }
            }
        }
    }

    public void recordOff(){
        if(this.controlConnection()) {
            if(status){
                System.out.println(this.getClass().getName() + " - " + getAlias() + " is turned off now");
                setStatus(false);
            }
            else{
                System.out.println(this.getClass().getName() + " - " + getAlias() + " has been already turned off");
            }
        }
    }

    @Override
    public boolean testObject() {
        if(this.controlConnection()) {
            SmartObjectToString();
            System.out.println("Test is starting for " + this.getClass().getName() + " day time");
            recordOn(true);
            recordOff();
            System.out.println("Test completed for " + this.getClass().getName());
            System.out.println("Test is starting for " + this.getClass().getName() + " night time");
            recordOn(false);
            recordOff();
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
                recordOff();
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean controlMotion(boolean hasMotion, boolean isDay) {
        if(hasMotion){
            System.out.println("Motion detected!");
            if(isDay){
                recordOn(isDay);
            }
            else{
                if(nightVision){
                    recordOn(isDay);
                }
            }
        }
        else{
            System.out.println("Motion not detected!");
        }
        return false;
    }

    @Override
    public int compareTo(SmartCamera smartCamera) {
        if(this.batteryLife > smartCamera.getBatteryLife()){
            return 1;
        }
        else if (this.batteryLife == smartCamera.getBatteryLife()) {
            return 0;
        }
        else{
            return -1;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public boolean isNightVision() {
        return nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }

    @Override
    public String toString(){
      return this.getClass().getName() + " -> " + getAlias() + "'s battery life is " + getBatteryLife() + " is recording" ;
    }
}
