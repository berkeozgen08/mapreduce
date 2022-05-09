package com.mapreduce.dateaverage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.mapreduce.JSONParser;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.json.JSONObject;

public class DateAverageMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	public static DateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.US);

	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		String valueString = value.toString();
		JSONObject obj = JSONParser.parse(valueString);
		String dateString = obj.getString("review_date");
		try {
			Date date = format.parse(dateString);
			if (date.before(format.parse("5 May 2020")) && date.after(format.parse("1 Jan 2020"))) {
				return;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			output.collect(
				new Text(
					obj.getString("movie")
				),
				new IntWritable(
					Integer.parseInt(
						obj.getString("rating")
					)
				)
			);
		} catch (Exception e) {}
	}
}