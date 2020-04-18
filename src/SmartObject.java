import java.util.Calendar;

public abstract class SmartObject {
    private String alias;
    private String macId;
    private String IP;
    private boolean connectionStatus;

    public SmartObject(){
    }

    public boolean connect(String IP){
        setIP(IP);
        setConnectionStatus(true);
        System.out.println(alias + " connection established");

        return true;
    }

    public boolean disconnect(){
        setIP(null);
        setConnectionStatus(false);

        return true;
    }

    public void SmartObjectToString(){
        System.out.println("This is " +this.getClass().getName() +" device "+alias );
        System.out.println("\tMacId: "+macId);
        System.out.println("\tIP: "+IP);
    }

    public boolean controlConnection(){
        if(!connectionStatus){
            System.out.println("This device is not connected. " + this.getClass().getName() +" -> "+ alias);
            return false;
        }
        return true;
    }

    public abstract boolean testObject();

    public abstract boolean shutDownObject();

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getCurrentTime(){
        Calendar now = Calendar.getInstance();
        String hour = String.valueOf(now.get(Calendar.HOUR));
        String minute = String.valueOf(now.get(Calendar.MINUTE));
        String second = String.valueOf(now.get(Calendar.SECOND));
        String currentTime = hour + ":"+ minute + ":"+ second;
        return currentTime;
    }

}