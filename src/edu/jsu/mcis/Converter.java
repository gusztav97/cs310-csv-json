package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import com.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Converter {
    
    /*
    
        Consider the following CSV data:
        
        "ID","Total","Assignment 1","Assignment 2","Exam 1"
        "111278","611","146","128","337"
        "111352","867","227","228","412"
        "111373","461","96","90","275"
        "111305","835","220","217","398"
        "111399","898","226","229","443"
        "111160","454","77","125","252"
        "111276","579","130","111","338"
        "111241","973","236","237","500"
        
        The corresponding JSON data would be similar to the following (tabs and other whitespace
        have been added for clarity).  Note the curly braces, square brackets, and double-quotes!
        
        {
            "colHeaders":["ID","Total","Assignment 1","Assignment 2","Exam 1"],
            "rowHeaders":["111278","111352","111373","111305","111399","111160","111276","111241"],
            "data":[[611,146,128,337],
                    [867,227,228,412],
                    [461,96,90,275],
                    [835,220,217,398],
                    [898,226,229,443],
                    [454,77,125,252],
                    [579,130,111,338],
                    [973,236,237,500]
            ]
        }
    
    */
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
        
        String results = "";
        
        try {
            
            CSVReader reader = new CSVReader(new StringReader(csvString));
            List<String[]> full = reader.readAll();
            Iterator<String[]> iterator = full.iterator();
            
            //JSONObject jsonObject = new JSONObject();
            LinkedHashMap<String,Object> jsonObject = new LinkedHashMap<>();
            jsonObject = new LinkedHashMap<>();
            
            // INSERT YOUR CODE HERE
            String[] line = iterator.next();
            String[] record;
            String[] headings;
            JSONArray colHeaders = new JSONArray(); // Column Headers
            JSONArray rowHeaders = new JSONArray(); // Row Headers
            JSONArray data=  new JSONArray(); // data
            JSONArray dataRow;
            String jsonString = "";
            
            for (int i = 0; i < line.length; ++i){
                String field = line[i];
                colHeaders.add(field);
            }
            //line = iterator.next();
            
            while (iterator.hasNext()){
                line = iterator.next();
                rowHeaders.add(line[0]);
                dataRow = new JSONArray();
                for(int i = 1; i < line.length; i++){
                    dataRow.add(Integer.parseInt(line[i]));
                }
                data.add(dataRow);
            }
            
            
            jsonObject.put("rowHeaders", rowHeaders); // wrong
            jsonObject.put("data", data); // wrong
            jsonObject.put("colHeaders", colHeaders);
            
            jsonString = JSONValue.toJSONString(jsonObject);

            results+=jsonString;
            
        }
        
        catch(IOException e) { return e.toString(); }
        
        return results.trim();
        
    }
    
    public static String jsonToCsv(String jsonString) {
        
        String results = "";
        
        try {
            
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject)parser.parse(jsonString);
            
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer, ',', '"', '\n');
            // INSERT YOUR CODE HERE
            
            JSONArray colHeaders = (JSONArray) jsonObject.get("colHeaders");
            JSONArray rowHeaders = (JSONArray) jsonObject.get("rowHeaders");
            JSONArray data = (JSONArray) jsonObject.get("data");
            
            
            //csvWriter.writeNext(csvData);
            String csvString = writer.toString();
            
            
            
            
            
            
            
        }
        
        catch(ParseException e) { return e.toString(); }
        
        return results.trim();
        
    }
	
}