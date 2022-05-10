package com.mapreduce.totalreviews;

import java.io.IOException;

import com.mapreduce.util.JSONParser;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class TotalReviewsMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);

	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		String valueString = value.toString();
		// String[] SingleCountryData = valueString.split(",");
		output.collect(new Text(JSONParser.parse(valueString).getString("reviewer")), one);
	}
}