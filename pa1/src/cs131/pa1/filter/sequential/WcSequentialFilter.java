package cs131.pa1.filter.sequential;

import java.util.LinkedList;

public class WcSequentialFilter extends SequentialFilter {

	private int words;
	private int lines;
	private int chars;
	
	

	public WcSequentialFilter() {
		output = new LinkedList<String>();
		output.add(0 + " " + 0 + " " + 0);
	}



	@Override
	protected String processLine(String line) {
		lines++;
		words += line.split(" ").length;
		chars += line.length();
		if (isDone()) {
			output.clear();
			return lines + " " + words + " " + chars;
		}
		return null;
	}

}
