package com.victor.mapreduce.inputformat;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class WholeMapper extends Mapper<NullWritable, BytesWritable, Text, BytesWritable> {

	Text k = new Text();

	@Override
	protected void setup(Mapper<NullWritable, BytesWritable, Text, BytesWritable>.Context context)
			throws IOException, InterruptedException {
		FileSplit split = (FileSplit) context.getInputSplit();

		Path path = split.getPath();
		String name = path.getName();
		k.set(path.toString());

	}

	@Override
	protected void map(NullWritable key, BytesWritable value, Context context)
			throws IOException, InterruptedException {

		context.write(k, value);
	}
}
