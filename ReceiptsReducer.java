package Receipts;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import java.io.IOException;


public class ReceiptsReducer  extends Reducer <Text,Text,Text,FloatWritable> {
   private static Text tempYear=new Text();
   private static Text keyText=new Text();
   private static Text minYear=new Text();
   private static Text maxYear=new Text();
   private static String tempString;
   private static String[] keyString;

   public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
     // TODO declare and initialize int values for sum and count

     long tempValue = 0L;
     long max=Long.MIN_VALUE;
     long min=Long.MAX_VALUE;
     
     // decalre sum and count
     long sum = 0;
     long count = 0;

     for (Text value: values) {
         tempString = value.toString();
         keyString = tempString.split("_"); 
         tempYear = new Text(keyString[0]);
         tempValue = new Long(keyString[1]).longValue(); 
        if(tempValue < min) {
            min=tempValue;
            minYear=tempYear;
         }
         if(tempValue > max) {
            max=tempValue;
            maxYear = tempYear;
         }
         // TODO increment the sum by tempValue
         sum += tempValue;
         // TODO increment the count by 1 
         count += 1;
     }
     // TODO declare and calculate float value for mean

     // declare a float value called mean
     float mean = sum/count;

     keyText.set("min" + "(" + minYear.toString() + "): ");
     context.write(keyText, new FloatWritable(min)); 
     keyText.set("max" + "(" + maxYear.toString() + "): ");
     context.write(keyText, new FloatWritable(max)); 

     // TODO set the keyText to "mean:"
     keyText.set("mean: ");
     // TODO write keyText and mean to the context 
     context.write(keyText, new FloatWritable(mean)); 
   }
}
