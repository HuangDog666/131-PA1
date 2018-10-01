package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.LinkedList;

public class LsSequentialFilter extends SequentialFilter {

	public LsSequentialFilter() {
		init();
	}

	private void init() {
		String[] files = new File(SequentialREPL.currentWorkingDirectory).list();
		input = new LinkedList<String>();
		if (files == null) {
			return;
		}
		//put all files into the input queue
		for (String file : files) {
			if (file.startsWith(".")) {
				continue;
			}
			input.add(file);
		}
	}

	@Override
	protected String processLine(String line) {
		return line + "\n";
	}

}
