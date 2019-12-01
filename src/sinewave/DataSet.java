package sinewave;
public class DataSet {
    private int ID;
    private float Voltage;
    private int StartTime;
    private int EndTime;
    private int Type;
    
    public DataSet(){
        
    }
    
    public DataSet(int ID, float Voltage, int StartTime, int EndTime, int Type) {
        this.ID = ID;
        this.Voltage = Voltage;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Type = Type;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getVoltage() {
        return Voltage;
    }

    public void setVoltage(float Voltage) {
        this.Voltage = Voltage;
    }

    public int getStartTime() {
        return StartTime;
    }

    public void setStartTime(int StartTime) {
        this.StartTime = StartTime;
    }

    public int getEndTime() {
        return EndTime;
    }

    public void setEndTime(int EndTime) {
        this.EndTime = EndTime;
    }
    
    @Override
    public String toString()
    {
        return "ID: "+getID()+"\nVoltage: "+ getVoltage()+"\nStart Time: "+getStartTime()+"\nEnd Time: "+getEndTime();
    }
    
}
