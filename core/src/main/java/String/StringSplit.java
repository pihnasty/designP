package String;

public class StringSplit {
    public static void main(String[] args) {


        String dataTypeFull = "NUMBER";

        String splits[] = dataTypeFull.split("\\(");
        String dataType = splits[0].toUpperCase().trim();

        String dataTypeParams [] = splits[1].replaceAll("\\)","").split(",");
        String param1 = dataTypeParams[0].trim();
        String param2 = "";
        if (dataTypeParams.length > 1) {
            param2 = dataTypeParams[1].trim();
        }

        System.out.println();
    }
}
