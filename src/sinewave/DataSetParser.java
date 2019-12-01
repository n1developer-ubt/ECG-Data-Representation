package sinewave;
public class DataSetParser {

    public static DataSet ParseData(String data) {
        try {
            if(data.charAt(0) != '{')
                return null;
        } catch (Exception e) {
            return null;
        }
        
        StringBuilder builder = new StringBuilder(data);
        
        builder = builder.deleteCharAt(0);
        builder = builder.deleteCharAt(builder.length()-1);
        
        String[] objects = builder.toString().split(",");
        
        DataSet newDataSet = new DataSet();
        
        for(String object : objects)
        {
            String[] objectData = object.trim().split("=");
            
            if(objectData.length > 1)
            {
                String objectName = objectData[0];
                String objectValue = objectData[1];
                switch(objectName.toLowerCase().trim())
                {
                    case "id":
                        newDataSet.setID(Integer.parseInt(objectValue));
                        break;
                    case "title":
                        if(objectValue.trim().contains("R wave") || objectValue.trim().contains("Q wave") || objectValue.trim().contains("S wave"))
                            newDataSet.setType(1);
                        else
                            newDataSet.setType(0);
                        break;
                    case "voltage":
                        if(objectValue.contains("~"))
                        {
                            newDataSet.setVoltage(-Float.parseFloat(objectValue.replace("~", "")));
                        }else
                        {
                            newDataSet.setVoltage(Float.parseFloat(objectValue));
                        }
                        break;
                    case "start_time":
                        newDataSet.setStartTime((int)Float.parseFloat(objectValue));
                        break;
                        
                    case "end_time":
                        newDataSet.setEndTime((int)Float.parseFloat(objectValue));
                        break;
                }
                
            }
            
        }
        return newDataSet;
    }
    
}
